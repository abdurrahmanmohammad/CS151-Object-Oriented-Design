package telephone;

import voicemailSystem.VoicemailSystem;

public class Telephone {
	public static void main(String[] args) {
		VoicemailSystem voicemailSystem = new VoicemailSystem(); /** Create a voicemail system */
		voicemailSystem.initiate(); /** Initiate the voicemail system */
		/**
		 * Voicemail system password is 789. To test this system, you may follow any of
		 * the scenarios of the voicemail system. I have tested the admin menu and all
		 * its operations work. I have tested leaving a message and playing messages and
		 * that should work too. Adding and setting default greeting works. Messages
		 * management works also. Feel free to test any scenario.
		 */
	}
}
