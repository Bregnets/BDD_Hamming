import java.util.Random;

public class App {

	static public String flip(String data) {

		Random r = new Random();

		int index = r.nextInt(data.length());

		char errBit = '0';

		if (data.charAt(index) == '0') {
			errBit = '1';
		}

		String errData = "";

		for (int i = 0; i < data.length(); i++) {
			if (i == index) {
				errData += errBit;
			} else {
				errData += data.charAt(i);
			}
		}

		return errData;
	}

	static public void main(String[] args) {
		String msg = args[0];
		String encoded = HammingCode.encode(msg);
		String received = flip(encoded);
		String decoded = HammingCode.decode(received);
		System.out.println("Message length: " + msg.length());
		System.out.println("Encoded message length: " + encoded.length());
		System.out.println("Sending message: " + msg);
		System.out.println("Encoded message: " + encoded);
		System.out.println("Received message: " + received);
		System.out.println("Decoded message: " + decoded);
	}
}