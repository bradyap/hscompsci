import javax.sound.midi.*;

public class Sound
{
//sound stuff
   protected static MidiChannel[] channels=null;		//MIDI channels
   protected static Instrument[] instr;					//MIDI instrument bank
   public static int soundOffFrame, nextNoteFrame;    //frame number to turn sound off, or go to the next note for melody notes
   public static int nextNoteFrame2;                  //frame number to go to the next note for pedal tones
   protected static final int PIANO = 0;
   protected static final int HARPSICHORD = 6;
   protected static final int CELESTA = 8;
   protected static final int VIBRAPHONE = 11;
   protected static final int XYLOPHONE = 13;
   protected static final int NYLON_GUIT = 24;
   protected static final int STEEL_GUIT = 25;
   protected static final int GUIT_HARM = 31;
   protected static final int ORCH_HIT = 55;
   protected static final int SHAKUHACHI = 57;
   protected static final int SPACE_VOICE = 91;
   protected static final int SITAR = 104;
   protected static final int SHAMISEN = 106;
   protected static final int KALIMBA = 108;
   protected static final int TINKERBELL = 112;
   protected static final int STEEL_DRUM = 114;
   protected static final int WOODBLOCK = 115;
   protected static final int TAIKO = 116;
   protected static final int MELO_TOM = 117;
   protected static final int SYNTH_DRUM = 118;
   protected static final int REVERSE_CYM = 119;
   protected static final int FRET_NOISE = 120;
   protected static final int BREATH_NOISE = 121;
   protected static final int SEA_SHORE = 122;
   protected static final int BIRD = 123;
   protected static final int HELICOPTER = 125;
   protected static final int APPLAUSE = 126;
   protected static final int GUNSHOT = 127;

   protected static final int [] majPent = {0,2,4,7,9,12};
   protected static final int [] minPent = {0,3,5,7,10,12};
   protected static final int [] harmMin = {0,2,3,5,7,8,11,12};
   protected static final int [] mixolydian = {0,2,4,5,7,9,10,12};
   protected static int [] noteLengths;
   protected static int [] scale;      //melody notes
   protected static int [] pedalTones; 
   protected static int [] sustainLengths;

   protected static final int OCTAVE = 12;
  
   public static void initialize()
   {
   //sound stuff
      try 
      {
         Synthesizer synth = MidiSystem.getSynthesizer();
         synth.open();
         Sound.channels = synth.getChannels();
         Sound.instr = synth.getDefaultSoundbank().getInstruments();
      }
      catch (Exception ignored) 
      {}
      channels[0].programChange(instr[PIANO].getPatch().getProgram());
      channels[1].programChange(instr[PIANO].getPatch().getProgram());
      channels[2].programChange(instr[PIANO].getPatch().getProgram());
   
      buildScale(harmMin, minPent);
      noteLengths = new int[9];
      int wholeNote = CultimaPanel.animDelay*12;
      noteLengths[0] = Math.max(1,wholeNote/16);
      noteLengths[1] = Math.max(2,wholeNote/8);
      noteLengths[2] = Math.max(2,wholeNote/8);
      noteLengths[3] = Math.max(4,wholeNote/4);
      noteLengths[4] = Math.max(4,wholeNote/4);
      noteLengths[5] = Math.max(4,wholeNote/4);
      noteLengths[6] = Math.max(8,wholeNote/2);
      noteLengths[7] = Math.max(8,wholeNote/2);
      noteLengths[8] = Math.max(16,wholeNote);
   
      sustainLengths = new int[8];
      sustainLengths[0] = Math.max(16,wholeNote);
      sustainLengths[1] = Math.max(16,wholeNote);
      sustainLengths[2] = Math.max(16,wholeNote);
      sustainLengths[3] = Math.max(8,wholeNote/2);
            
      sustainLengths[4] = Math.max(16,wholeNote);
      sustainLengths[5] = Math.max(8,wholeNote/2);
      sustainLengths[6] = Math.max(8,wholeNote/2);
      sustainLengths[7] = Math.max(4,wholeNote/4);
   
      channels[0].allNotesOff();		//turn sounds off 
      channels[1].allNotesOff();		//turn sounds off 
      channels[2].allNotesOff();		//turn sounds off 
   
      soundOffFrame = Integer.MAX_VALUE;
      nextNoteFrame = 0;
      nextNoteFrame2 = 0;
   
   }

 //pre:  numTracks >= 0 && numTracks < channels.length
 //post: turns sound of numTracks number of channels
   public static void silence(int numTracks)
   {
      for(int i=0; i<numTracks && i<channels.length; i++)
      {
         channels[i].allNotesOff();		//turn sounds off 
      }
   }

   public static void checkToClearSound()
   {
      if(CultimaPanel.numFrames > soundOffFrame)
      {
         silence(2);						//turn sounds off 
         soundOffFrame = Integer.MAX_VALUE;
      }
   }

//play ambient sounds in locations
   public static void runAmbientSounds()
   {
      if(!CultimaPanel.soundOn)
         return;
      String locType = CultimaPanel.player.getLocationType().toLowerCase();
      if(locType.contains("graboid"))
      {
         channels[0].programChange(instr[HELICOPTER].getPatch().getProgram());
         int pitch = Player.rollDie(8,10);          
         int velocity = Player.rollDie(120);
         channels[0].noteOn(pitch, velocity);
      }
      else if(CultimaPanel.getDeathAbout!=null)
      {
         if(Math.random() < 0.03)
         {
            channels[0].programChange(instr[APPLAUSE].getPatch().getProgram());
            int pitch = Player.rollDie(90,100);          
            int velocity = Player.rollDie(80,100);
            channels[0].noteOn(pitch, velocity);
            soundOffFrame = CultimaPanel.numFrames + Player.rollDie(20,40);
         }
      }
      else if(CultimaPanel.player.getWeapon().getName().toLowerCase().contains("lute"))
      {
         if(CultimaPanel.numFrames > nextNoteFrame)
         {
            channels[0].programChange(instr[STEEL_GUIT].getPatch().getProgram());
            int note = scale[(int)(Math.random()*scale.length)];
            int velocity = 40;
            channels[0].noteOn(note, velocity);
            int noteLength = noteLengths[(int)(Math.random()*(noteLengths.length))]/2;
            nextNoteFrame = CultimaPanel.numFrames + noteLength;  //faster notes
            soundOffFrame = CultimaPanel.numFrames + noteLength;
         }
         if(CultimaPanel.numFrames > nextNoteFrame2)
         {
            channels[0].programChange(instr[STEEL_GUIT].getPatch().getProgram());
            int noteIndex = (int)(Math.random()*pedalTones.length);
            int noteLength = sustainLengths[(int)(Math.random()*(sustainLengths.length))];
            int note = pedalTones[noteIndex] - OCTAVE;
            int velocity = 40;
            channels[0].noteOn(note, velocity);
            nextNoteFrame2 = CultimaPanel.numFrames + noteLength;  //sustained notes
            soundOffFrame = CultimaPanel.numFrames + noteLength;
         }
      }
      else if((CultimaPanel.player.getMapIndex()==0 || locType.contains("domicile") || locType.contains("temple") || locType.contains("jurassica")) && CultimaPanel.weather <= 2)
      {
         if(locType.contains("temple") && CultimaPanel.player.getWorldRow()==50 && CultimaPanel.player.getWorldCol()==50)
         {  //endgame music
            if(CultimaPanel.numFrames > nextNoteFrame)
            {
               channels[0].programChange(instr[SPACE_VOICE].getPatch().getProgram());
               int note = scale[(int)(Math.random()*scale.length)]/2;
               int velocity = 80;
               channels[0].noteOn(note, velocity);
               int noteLength = noteLengths[(int)(Math.random()*(noteLengths.length-2))+2]/2;
               nextNoteFrame = CultimaPanel.numFrames + noteLength; 
               soundOffFrame = CultimaPanel.numFrames + noteLength;
            }
         }
         else
         {  //regular world, temple or domicile
            if(CultimaPanel.phantomAbout)
            {
               if(Math.random() < 0.01)
               {
                  channels[0].programChange(instr[SITAR].getPatch().getProgram());
                  channels[0].noteOn((int)(Math.random()*10), (int)(Math.random()*20)+60);
               }
            }
            else if(CultimaPanel.time < 20 && CultimaPanel.time > 6 )    //day - birds chirping
            {
               if(Math.random() < 0.005)
               {
                  channels[0].programChange(instr[BIRD].getPatch().getProgram());
                  int pitch = (int)(Math.random()*13)+58;
                  int velocity = (int)(Math.random()*20)+50;
                  boolean dinoSound = (Math.random() < 0.25);
                  if(locType.contains("jurassica") && dinoSound)
                  {
                     pitch = (int)(Math.random()*5)+15;
                     velocity = (int)(Math.random()*20)+80;
                  }
                  channels[0].noteOn(pitch, velocity);
                  if(!locType.contains("jurassica") || !dinoSound)
                     soundOffFrame = CultimaPanel.numFrames + Player.rollDie(3,10);
               }
            }
            else if(CultimaPanel.numFrames % (CultimaPanel.animDelay*6) == 0 || CultimaPanel.numFrames % ((CultimaPanel.animDelay*6) + (CultimaPanel.animDelay*3/16)) == 0)    //night - crickets
            {
               if(locType.contains("jurassica"))
               {
                  channels[0].programChange(instr[APPLAUSE].getPatch().getProgram());
                  int pitch = (int)(Math.random()*5)+103;
                  int velocity = (int)(Math.random()*20)+50;
                  channels[0].noteOn(pitch, velocity);
                  soundOffFrame = CultimaPanel.numFrames + Player.rollDie(10,12);
               }
               else if(!Display.isWinter())
               {
                  channels[0].programChange(instr[BIRD].getPatch().getProgram());
                  channels[0].noteOn((int)(Math.random()*3)+78, (int)(Math.random()*10)+40);
                  soundOffFrame = CultimaPanel.numFrames + Player.rollDie(3,5);
               }
            }
         }
      }
      else if(locType.contains("city") || locType.contains("fort") || locType.contains("port") || locType.contains("village"))
      {
         if(CultimaPanel.time < 20 && CultimaPanel.time > 6 )    //day - music
         {
            boolean monsterAbout = (CultimaPanel.monsterAndCivInView!=null);
            boolean zombieAbout = CultimaPanel.zombieAbout;
            if(CultimaPanel.numFrames > nextNoteFrame)
            {
               if(locType.contains("village"))
                  channels[0].programChange(instr[PIANO].getPatch().getProgram());
               else
                  channels[0].programChange(instr[NYLON_GUIT].getPatch().getProgram());
               int note = scale[(int)(Math.random()*scale.length)];
               int velocity = Player.rollDie(30,40);
               if(monsterAbout || zombieAbout)    //lower notes if there is a monster in town
               {
                  note = Math.max(22, note/2);
                  velocity = Player.rollDie(40,50);
               }
               if(!monsterAbout && CultimaPanel.weather > 0)
               {}       //no lute players out if it is raining, but play menacing music if there is a monster in town
               else
                  channels[0].noteOn(note, velocity);
               int noteLength = noteLengths[(int)(Math.random()*noteLengths.length)];
               if(monsterAbout)    //faster notes if there is a monster in town
                  noteLength =  noteLengths[(int)(Math.random()*noteLengths.length/2)];
               else     //slower notes in morning and night, faster notes in midday
               {
                  double distFromCenterTime = Math.abs(CultimaPanel.time - ((20 + 6)/2.0));
                  noteLength = (int)(noteLength*((distFromCenterTime/7.0 + 0.5)));
               }
               nextNoteFrame = CultimaPanel.numFrames + noteLength;
               soundOffFrame = CultimaPanel.numFrames + noteLength;
            }
            if(!monsterAbout && !zombieAbout)    //play pedal tones
            {
               if(CultimaPanel.numFrames > nextNoteFrame2)
               {
                  if(locType.contains("village"))
                     channels[0].programChange(instr[PIANO].getPatch().getProgram());
                  else
                     channels[0].programChange(instr[NYLON_GUIT].getPatch().getProgram());
                  int noteIndex = (int)(Math.random()*pedalTones.length);
                  int noteLength = sustainLengths[(int)(Math.random()*(sustainLengths.length))];
                  if(CultimaPanel.days%3==1 && CultimaPanel.weather==0)
                  {
                     noteIndex = 0;    //always the root for the pedal tone
                     noteLength = sustainLengths[0];
                  }
                  else if(CultimaPanel.days%3==2 && Math.random()<0.5)
                  {
                     noteIndex = 0;
                     noteLength = sustainLengths[(int)(Math.random()*(sustainLengths.length/2))];
                  }
                  int note = pedalTones[noteIndex] - OCTAVE;
                  int velocity = Player.rollDie(30,40);
                  channels[0].noteOn(note, velocity);
                  nextNoteFrame2 = CultimaPanel.numFrames + noteLength;  //sustained notes
                  soundOffFrame = CultimaPanel.numFrames + noteLength;
               }
            }
         }
         else
         { 
            if(CultimaPanel.weather <= 2)
            {
               if(CultimaPanel.numFrames % (CultimaPanel.animDelay*6) == 0 || CultimaPanel.numFrames % ((CultimaPanel.animDelay*6) + (CultimaPanel.animDelay*3/16)) == 0)    //night - crickets
               {
                  if(!Display.isWinter())
                  {
                     channels[0].programChange(instr[BIRD].getPatch().getProgram());
                     channels[0].noteOn((int)(Math.random()*3)+78, (int)(Math.random()*10)+40);
                     soundOffFrame = CultimaPanel.numFrames + Player.rollDie(3,5);
                  }
               }
            }
            if(CultimaPanel.assassinAbout!=-1 && Math.random() < 0.01)
            {
               channels[0].programChange(instr[SITAR].getPatch().getProgram());
               channels[0].noteOn((int)(Math.random()*10), (int)(Math.random()*20)+60);
            }
         }
      }
      else if(locType.contains("castle") || locType.contains("tower"))
      {
         if(CultimaPanel.time < 20 && CultimaPanel.time > 6 )    //day - music
         {
            boolean zombieAbout = CultimaPanel.zombieAbout;
         
            if(CultimaPanel.numFrames > nextNoteFrame)
            {               
               channels[0].programChange(instr[HARPSICHORD].getPatch().getProgram());
               int note = scale[(int)(Math.random()*scale.length)];
               int velocity = 40;
               if(zombieAbout)
                  note /= 2;
               channels[0].noteOn(note, velocity);
               int noteLength = noteLengths[(int)(Math.random()*(noteLengths.length-2))];
                    //slower notes in morning and night, faster notes in midday
               double distFromCenterTime = Math.abs(CultimaPanel.time - ((20 + 6)/2.0));
               noteLength = (int)(noteLength*((distFromCenterTime/7.0 + 0.5)));
               nextNoteFrame = CultimaPanel.numFrames + noteLength;  //faster notes in a castle
               soundOffFrame = CultimaPanel.numFrames + noteLength;
            }
            if(!zombieAbout)    //play pedal tones
            {
               if(CultimaPanel.numFrames > nextNoteFrame2)
               {
                  channels[0].programChange(instr[HARPSICHORD].getPatch().getProgram());
                  int noteIndex = (int)(Math.random()*pedalTones.length);
                  int noteLength = sustainLengths[(int)(Math.random()*(sustainLengths.length))];
                  if(CultimaPanel.days%3==1 && CultimaPanel.weather==0)
                  {
                     noteIndex = 0;    //always the root for the pedal tone
                     noteLength = sustainLengths[0];
                  }
                  else if(CultimaPanel.days%3==2 && Math.random()<0.5)
                  {
                     noteIndex = 0;
                     noteLength = sustainLengths[(int)(Math.random()*(sustainLengths.length/2))];
                  }
                  int note = pedalTones[noteIndex] - OCTAVE;
                  int velocity = Player.rollDie(30,40);
                  channels[0].noteOn(note, velocity);
                  nextNoteFrame2 = CultimaPanel.numFrames + noteLength;  //sustained notes
                  soundOffFrame = CultimaPanel.numFrames + noteLength;
               }
            }
         }
         else
         { 
            if(CultimaPanel.assassinAbout!=-1 && Math.random() < 0.01)
            {
               channels[0].programChange(instr[SITAR].getPatch().getProgram());
               channels[0].noteOn((int)(Math.random()*10), (int)(Math.random()*20)+60);
            }
         }
      }
      else if(locType.contains("lair") && Math.random() < 0.01)
      {
         int pitch=0, velocity=0;
         if(Math.random() < 0.5)
         {
            channels[0].programChange(instr[FRET_NOISE].getPatch().getProgram());
            pitch = (int)(Math.random()*20);
            velocity = (int)(Math.random()*60)+50;
         }
         else
         {
            channels[0].programChange(instr[BIRD].getPatch().getProgram());
            pitch = (int)(Math.random()*11);
            velocity = (int)(Math.random()*10)+90;
         }
         channels[0].noteOn(pitch, velocity);
      }
      else if(locType.contains("mine") && Math.random() < 0.01)
      {
         channels[0].programChange(instr[HELICOPTER].getPatch().getProgram());
         int pitch = (int)(Math.random()*15);
         int velocity = (int)(Math.random()*20)+90;
         channels[0].noteOn(pitch, velocity);
         soundOffFrame = CultimaPanel.numFrames + Player.rollDie(40,80);
      }
      else if(locType.contains("cave") && Math.random() < 0.01)
      {
         int pitch = (int)(Math.random()*30);
         int velocity = (int)(Math.random()*60)+50;
         if(Math.random() < 0.5)
         {
            channels[0].programChange(instr[BREATH_NOISE].getPatch().getProgram());
            pitch = (int)(Math.random()*30);
            velocity = (int)(Math.random()*60)+50;
         }
         else
         {
            channels[0].programChange(instr[SEA_SHORE].getPatch().getProgram());
            pitch = (int)(Math.random()*20);
            velocity = (int)(Math.random()*30)+50;
         }
         channels[0].noteOn(pitch, velocity);
      }
      else if(locType.contains("dungeon") && Math.random() < 0.01)
      {
         int pitch=0, velocity=0;
         if(Math.random() < 0.5)
         {
            channels[0].programChange(instr[FRET_NOISE].getPatch().getProgram());
            pitch = (int)(Math.random()*20);
            velocity = (int)(Math.random()*60)+50;
         }
         else
         {
            channels[0].programChange(instr[ORCH_HIT].getPatch().getProgram());
            pitch = (int)(Math.random()*26)+10;
            velocity = (int)(Math.random()*10)+90;
         }
         channels[0].noteOn(pitch, velocity);
      }
      else if(locType.contains("underworld") && Math.random() < 0.02)
      {
         channels[0].programChange(instr[APPLAUSE].getPatch().getProgram());
         int pitch = Player.rollDie(6,12);          
         int velocity = Player.rollDie(80,100);
         channels[0].noteOn(pitch, velocity);
         soundOffFrame = CultimaPanel.numFrames + Player.rollDie(40,80);
      }
         //end playing ambient sounds
   }

   public static void buildScale(int [] baseScale, int [] pedalScale)
   {
      int randKey = Player.rollDie(60, 71);
      
      int [] ans = new int[baseScale.length];
      for(int i=0; i<ans.length; i++)
         ans[i] = baseScale[i] + randKey;
      scale = ans;
      
      ans = new int[pedalScale.length];
      for(int i=0; i<ans.length; i++)
         ans[i] = pedalScale[i] + randKey;
      pedalTones = ans;
   }

//sound for rain - weather >= 3 and weather <=10
   public static void rain(int weather)
   {
      channels[2].allNotesOff();
      if(!CultimaPanel.soundOn)
         return;
      if(weather >= 3 && !Display.isWinter())
      {
         channels[2].programChange(instr[APPLAUSE].getPatch().getProgram());
         int pitch = 30 + weather;          
         int velocity = 30 + (weather*3);
         channels[2].noteOn(pitch, velocity);
      }
   }
   
   public static void applauseWin(int duration)
   {
      //channels[2].allNotesOff();
      boolean open = false;  
      if(CultimaPanel.time >= 6 && CultimaPanel.time <= 20)
         open = true;
      if(!CultimaPanel.soundOn || CultimaPanel.weather >= 3 || !open)
         return;
      channels[0].programChange(instr[APPLAUSE].getPatch().getProgram());
      int pitch = 55 + Player.rollDie(1,5);          
      int velocity = Player.rollDie(70,100);
      channels[0].noteOn(pitch, velocity);
      soundOffFrame = CultimaPanel.numFrames + duration;
   }

   public static void applauseSuprise(int duration)
   {
      //channels[2].allNotesOff();
      boolean open = false;  
      if(CultimaPanel.time >= 6 && CultimaPanel.time <= 20)
         open = true;
      if(!CultimaPanel.soundOn || CultimaPanel.weather >= 3 || !open)
         return;
      channels[0].programChange(instr[APPLAUSE].getPatch().getProgram());
      int pitch = 50 + Player.rollDie(1,5);          
      int velocity = Player.rollDie(65,80);
      channels[0].noteOn(pitch, velocity);
      soundOffFrame = CultimaPanel.numFrames + duration;
   }
   
   public static void timestop()
   {
      channels[2].allNotesOff();
      if(!CultimaPanel.soundOn)
         return;
      channels[2].programChange(instr[SPACE_VOICE].getPatch().getProgram());
      int pitch = Player.rollDie(30,35);          
      int velocity = 127;
      channels[2].noteOn(pitch, velocity);
   }

//scroll weapon, armor, potion, spell, item
   public static void scrollItem()
   {
      if(!CultimaPanel.soundOn)
         return;
      channels[1].programChange(instr[FRET_NOISE].getPatch().getProgram());
      int pitch = 100;          
      int velocity = 50;
      channels[1].noteOn(pitch, velocity);
   }

//sound for dropping weapon, armor or item
   public static void dropItem()
   {
      if(!CultimaPanel.soundOn)
         return;
      channels[1].programChange(instr[KALIMBA].getPatch().getProgram());
      int pitch = 20;          
      int velocity = 50;
      channels[1].noteOn(pitch, velocity);
   }

//scroll item in armory or magic shoppe
   public static void scrollShoppeItem()
   {
      if(!CultimaPanel.soundOn)
         return;
      channels[1].programChange(instr[WOODBLOCK].getPatch().getProgram());
      int pitch = 100;          
      int velocity = 40;
      channels[1].noteOn(pitch, velocity);
   }

//hammer to break wall sound
   public static void hammerWall()
   {
      if(!CultimaPanel.soundOn)
         return;
      channels[1].programChange(instr[WOODBLOCK].getPatch().getProgram());
      int pitch = Player.rollDie(12,13);          
      int velocity = 80;
      channels[1].noteOn(pitch, velocity);
   }

//click when successfully picking a lock or disarming a trap
   public static void pickLock()
   {
      if(!CultimaPanel.soundOn)
         return;
      channels[1].programChange(instr[FRET_NOISE].getPatch().getProgram());
      int pitch = 90;          
      int velocity = 100;
      channels[1].noteOn(pitch, velocity);
   }

//sound of springing a trap and avoiding its damage
   public static void trapAvoided()
   {
      if(!CultimaPanel.soundOn)
         return;
      channels[1].programChange(instr[BREATH_NOISE].getPatch().getProgram());
      channels[1].noteOn(Player.rollDie(37,41), 100);        
   }

//sound of getting damaged by a trap being sprung
   public static void trapSprung()
   {
      if(!CultimaPanel.soundOn)
         return;
      channels[1].programChange(instr[FRET_NOISE].getPatch().getProgram());
      channels[1].noteOn(Player.rollDie(37,41), 100);        
   }
   
   //sound of having an item eaten by a rustcreature
   public static void itemEaten()
   {
      if(!CultimaPanel.soundOn)
         return;
      channels[1].programChange(instr[FRET_NOISE].getPatch().getProgram());
      channels[1].noteOn(Player.rollDie(32,37), 100);        
   }

   public static void openDoor()
   {
      if(!CultimaPanel.soundOn)
         return;
      channels[1].programChange(instr[CELESTA].getPatch().getProgram());
      int pitch = Player.rollDie(10,20);          
      int velocity = 80;
      channels[1].noteOn(pitch, velocity);
   }

//sound for breaking through a secret wall or going through a secret passage
   public static void secretWall()
   {
      if(!CultimaPanel.soundOn)
         return;
      channels[1].programChange(instr[SHAMISEN].getPatch().getProgram());
      int pitch = Player.rollDie(15,20);          
      int velocity = 60;
      channels[1].noteOn(pitch, velocity);
   }

//sound for getting on, off horse, or setting up camp
   public static void getOnHorse()
   {
      if(!CultimaPanel.soundOn)
         return;
      channels[1].programChange(instr[WOODBLOCK].getPatch().getProgram());
      channels[1].noteOn(60, 50);        
   }

   	//sound for dragon spawning on map
   public static void dragonCall()
   {
      if(!CultimaPanel.soundOn)
         return;
      channels[1].programChange(instr[BIRD].getPatch().getProgram());
      int pitch = (int)(Math.random()*5)+30;          
      int velocity = (int)(Math.random()*10)+90;
      channels[1].noteOn(pitch, velocity);
   } 

//sound for NPC getting killed, different for different size NPC (1, 1.5 or 2)
   public static void NPCdie(double size)
   {
      if(!CultimaPanel.soundOn)
         return;
      channels[1].programChange(instr[TAIKO].getPatch().getProgram());
      int pitch = 40;
      int velocity = 80;
      if(size == 1.5)
      {
         pitch = 35;
         velocity = 90;
      }
      else if(size == 2)
      {
         pitch = 30;
         velocity = 100;
      }
      channels[1].noteOn(pitch, velocity);        
   }

//damage sound for NPC player, different depending on amount of damage relative to how much health they have left
   public static void NPCdamage(double d, double body)
   {
      if(!CultimaPanel.soundOn)
         return;
      channels[1].programChange(instr[MELO_TOM].getPatch().getProgram());
      double relativeDamage = d/body;    //percentage of damage to whole potential
      int pitch = Math.min(40, (int)(80*relativeDamage));
      int velocity = Math.min(100, (int)(200*relativeDamage));
      velocity = Math.max(50, velocity);
      channels[1].noteOn(pitch, velocity);        
   }

//damage sound for player, changes depending on the amount of damage relative to the player's max health potential
   public static void playerDamage(int d)
   {
      if(!CultimaPanel.soundOn)
         return;
      channels[1].programChange(instr[SYNTH_DRUM].getPatch().getProgram());
      double relativeDamage = d/((double)(CultimaPanel.player.getHealthMax()));    //percentage of damage to whole potential
      int pitch = Math.min(40, (int)(80*relativeDamage));
      int velocity = Math.min(100, (int)(200*relativeDamage));
      channels[1].noteOn(pitch, velocity);        
   }
   
   public static void vampAttack(double d, double body)
   {
      if(!CultimaPanel.soundOn)
         return;
      channels[1].programChange(instr[FRET_NOISE].getPatch().getProgram());
      double relativeDamage = d/body;    //percentage of damage to whole potential
      int pitch = Player.rollDie(100, 104);          
      int velocity = Math.min(100, (int)(200*relativeDamage));
      channels[1].noteOn(pitch, velocity);
   }

//the sound if we are trying to attack during our reload time
   public static void reloadTime()
   {
      if(!CultimaPanel.soundOn)
         return;
      channels[1].programChange(instr[WOODBLOCK].getPatch().getProgram());
      channels[1].noteOn(50, 50);
   }

//sound of us missing with a weapon attack, avoiding an enemie's attack
   public static void miss()
   {
      if(!CultimaPanel.soundOn)
         return;
      channels[1].programChange(instr[BREATH_NOISE].getPatch().getProgram());
      channels[1].noteOn(Player.rollDie(40,45), Player.rollDie(70,80));        
   }
   
   public static void teleport()
   {
      if(!CultimaPanel.soundOn)
         return;
      channels[1].programChange(instr[REVERSE_CYM].getPatch().getProgram());
      int pitch = Player.rollDie(50, 60);
      int velocity = Player.rollDie(100, 120);
      channels[1].noteOn(pitch, velocity); 
   }

   public static void summonUndead()
   {
      if(!CultimaPanel.soundOn)
         return;
      channels[1].programChange(instr[REVERSE_CYM].getPatch().getProgram());
      int pitch = Player.rollDie(25, 45);
      int velocity = Player.rollDie(100, 120);
      channels[1].noteOn(pitch, velocity); 
   }

//sound for firing a ship's cannon
   public static void fireCannon()
   {
      if(!CultimaPanel.soundOn)
         return;
      channels[1].programChange(instr[GUNSHOT].getPatch().getProgram());
      channels[1].noteOn(35, 80);        
   }
   
   public static void fireGun()
   {
      if(!CultimaPanel.soundOn)
         return;
      channels[1].programChange(instr[GUNSHOT].getPatch().getProgram());
      channels[1].noteOn(50, 80);        
   }
   
   public static void thunder()
   {
      if(!CultimaPanel.soundOn)
         return;
      channels[1].programChange(instr[GUNSHOT].getPatch().getProgram());
      int pitch = Player.rollDie(5, 25);
      int velocity = Player.rollDie(100, 120);
      channels[1].noteOn(pitch, velocity);        
   }
   
   public static void explosion()
   {
      if(!CultimaPanel.soundOn)
         return;
      channels[1].programChange(instr[GUNSHOT].getPatch().getProgram());
      int pitch = Player.rollDie(3, 15);
      int velocity = Player.rollDie(80, 120);
      channels[1].noteOn(pitch, velocity);        
   }

//pre: distance is the monster's distance to the player
//post:sound for the stomping feet of a massive monster, louder if closer
   public static void stomp(int distance)
   {
      if(!CultimaPanel.soundOn)
         return;
      channels[1].programChange(instr[GUNSHOT].getPatch().getProgram());
      int pitch = Player.rollDie(8, 10);
      int velocity = Math.max(20, 100 - (distance*4));
      channels[1].noteOn(pitch, velocity);        
   }

   public static void castFlightSpell()
   {
      if(!CultimaPanel.soundOn)
         return;
      channels[1].programChange(instr[STEEL_DRUM].getPatch().getProgram());
      int pitch = 48;
      int velocity = 60;
      channels[1].noteOn(pitch, velocity);         
   }

   public static void castFearSpell()
   {
      if(!CultimaPanel.soundOn)
         return;
      channels[1].programChange(instr[STEEL_DRUM].getPatch().getProgram());
      int pitch = 24;
      int velocity = 100;
      channels[1].noteOn(pitch, velocity);         
   }

   public static void castSpell()
   {
      if(!CultimaPanel.soundOn)
         return;
      channels[1].programChange(instr[STEEL_DRUM].getPatch().getProgram());
      int pitch = 36;
      int velocity = 60;
      channels[1].noteOn(pitch, velocity);         
   }

   public static void drinkPotion()
   {
      if(!CultimaPanel.soundOn)
         return;
      channels[1].programChange(instr[XYLOPHONE].getPatch().getProgram());
      int pitch = Player.rollDie(40,50);          
      int velocity = 30;
      channels[1].noteOn(pitch, velocity);
   }

   public static void addWeapon()
   {
      if(!CultimaPanel.soundOn)
         return;
      channels[1].programChange(instr[GUNSHOT].getPatch().getProgram());
      int pitch = Player.rollDie(80,90);          
      int velocity = 30;
      channels[1].noteOn(pitch, velocity);
   }

   public static void addPotion()
   {
      if(!CultimaPanel.soundOn)
         return;
      channels[1].programChange(instr[TINKERBELL].getPatch().getProgram());
      int pitch = 75;          
      int velocity = 30;
      channels[1].noteOn(pitch, velocity);
   }

   public static void addArmor()
   {
      if(!CultimaPanel.soundOn)
         return;
      channels[1].programChange(instr[KALIMBA].getPatch().getProgram());
      int pitch = 15;          
      int velocity = 60;
      channels[1].noteOn(pitch, velocity);
   }

   public static void addSpell()
   {
      if(!CultimaPanel.soundOn)
         return;
      channels[1].programChange(instr[VIBRAPHONE].getPatch().getProgram());
      int pitch = Player.rollDie(35,40);          
      int velocity = 60;
      channels[1].noteOn(pitch, velocity);    
   }

//the more gold is added, the higher the pitch
   public static void addGold(int g)
   {
      if(!CultimaPanel.soundOn)
         return;
      channels[1].programChange(instr[TINKERBELL].getPatch().getProgram());
      int pitch = Math.min(100, Math.max(50,g*10));          
      int velocity = 40;
      channels[1].noteOn(pitch, velocity);
   }

  //for adding rations or arrows - buying, or taking off of corpse
   public static void addRations()
   {
      if(!CultimaPanel.soundOn)
         return;
      channels[1].programChange(instr[FRET_NOISE].getPatch().getProgram());
      int pitch = 102;          
      int velocity = 60;
      channels[1].noteOn(pitch, velocity);
   }


//sound of finding a map or being shown something on your map
   public static void mapFound()
   {
      if(!CultimaPanel.soundOn)
         return;
      channels[1].programChange(instr[BREATH_NOISE].getPatch().getProgram());
      int pitch = Player.rollDie(90,95);          
      int velocity = 40;
      channels[1].noteOn(pitch, velocity);
      soundOffFrame = CultimaPanel.numFrames + 10;
   }

//the sound of giving gold to another player to make a payment or bribe
   public static void payGold()
   {
      if(!CultimaPanel.soundOn)
         return;
      channels[1].programChange(instr[MELO_TOM].getPatch().getProgram());
      int pitch = 100;          
      int velocity = 50;
      channels[1].noteOn(pitch, velocity);
   }

//for leveling up, or gaining new skills like awareness, might, mind or agility
   public static void levelUp(int pitch)
   {
      if(!CultimaPanel.soundOn)
         return;
      channels[1].programChange(instr[ORCH_HIT].getPatch().getProgram());
      int velocity = 100;
      channels[1].noteOn(pitch, velocity);
   }

   public static void playerDie()
   {
      if(!CultimaPanel.soundOn)
         return;
      channels[1].programChange(instr[PIANO].getPatch().getProgram());
      channels[1].noteOn(22, 80);        
   }

//sound for when guards are alerted to a witnessed vandalism, theft or murder
   public static void alertGuards()
   {
      if(!CultimaPanel.soundOn)
         return;
      channels[1].programChange(instr[GUIT_HARM].getPatch().getProgram());
      channels[1].noteOn(Player.rollDie(22,25), 80);        
   }
}