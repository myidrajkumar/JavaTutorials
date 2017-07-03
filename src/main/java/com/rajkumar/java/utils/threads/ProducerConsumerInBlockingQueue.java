package com.rajkumar.java.utils.threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rajkumar.java.utils.lib.Utils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Producer / Consumer problem solution using blocking queue.
 * 
 * @author Rajkumar
 *
 */
public class ProducerConsumerInBlockingQueue {
  
  private static Logger logger = LogManager.getLogger();
  
  private ProducerConsumerInBlockingQueue() { }
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {
    
    // Creating BlockingQueue of size 10
    BlockingQueue<Message> queue = new ArrayBlockingQueue<>(10);
    Producer producer = new Producer(queue);
    Consumer consumer = new Consumer(queue);
    
    // starting producer to produce messages in queue
    new Thread(producer).start();
    // starting consumer to consume messages from queue
    new Thread(consumer).start();
    logger.info("Producer and Consumer has been started");
  }
  
  private static class Message {
    
    private String msg;
    
    public Message(String msg) {
      this.msg = msg;
    }
    
    public String getMsg() {
      
      return msg;
    }
    
  }
  
  private static class Producer implements Runnable {
    
    private BlockingQueue<Message> msgQueue;
    
    public Producer(BlockingQueue<Message> msgQueue) {
      this.msgQueue = msgQueue;
    }
    
    @Override
    public void run() {
      
      // produce messages
      for (int i = 0; i < 100; i++) {
        Message msg = new Message(Integer.toString(i));
        try {
          TimeUnit.MILLISECONDS.sleep(i);
          msgQueue.put(msg);
          logger.info("Produced " + msg.getMsg());
        } catch (InterruptedException exception) {
          logger.error(Utils.getException(exception));
          Thread.currentThread().interrupt();
        }
      }
      
      // adding exit message
      Message msg = new Message("exit");
      try {
        msgQueue.put(msg);
      } catch (InterruptedException exception) {
        logger.error(Utils.getException(exception));
        Thread.currentThread().interrupt();
      }
    }
  }
  
  private static class Consumer implements Runnable {
    
    private BlockingQueue<Message> msgQueue;
    
    public Consumer(BlockingQueue<Message> msgQueue) {
      this.msgQueue = msgQueue;
    }
    
    @Override
    public void run() {
      
      Message msg;
      // consuming messages until exit message is received
      try {
        while (!"exit".equals((msg = msgQueue.take()).getMsg())) {
          TimeUnit.MILLISECONDS.sleep(10);
          logger.info("Consumed " + msg.getMsg());
        }
        
        logger.info("Exit Messgae From Producer is '" + msg.getMsg() + "'");
      } catch (InterruptedException exception) {
        logger.error(Utils.getException(exception));
        Thread.currentThread().interrupt();
      }
    }
  }
  
}
