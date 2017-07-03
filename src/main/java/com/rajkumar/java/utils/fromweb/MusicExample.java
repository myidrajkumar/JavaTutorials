package com.rajkumar.java.utils.fromweb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rajkumar.java.utils.lib.Utils;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

/**
 * Music tone example.
 * 
 * @author Rajkumar
 *
 */
public class MusicExample {

  private static Logger logger = LogManager.getLogger();
  
  private MusicExample() { }
  
  /**
   * Main Method.
   * 
   * @param args empty arguments
   */
  public static void main(String[] args) {

    MusicExample music = new MusicExample();
    music.play();
  }

  private void play() {
        
    try (Sequencer player = MidiSystem.getSequencer()) {
            
      player.open();
            
      Sequence seq = new Sequence(Sequence.PPQ, 4);
            
      Track track = seq.createTrack();
            
      ShortMessage msg1 = new ShortMessage();
      msg1.setMessage(144, 1, 44, 100);
            
      MidiEvent noteOn = new MidiEvent(msg1, 1);
      track.add(noteOn);
            
      ShortMessage msg2 = new ShortMessage();
      msg2.setMessage(128, 1, 44, 100);
            
      MidiEvent noteOff = new MidiEvent(msg2, 16);
      track.add(noteOff);
            
      player.setSequence(seq);
      player.start();
            
    } catch (MidiUnavailableException | InvalidMidiDataException exception) {
      logger.error(Utils.getException(exception));
    }
  }
}
