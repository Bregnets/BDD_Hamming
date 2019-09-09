public class HammingCode {

	static public String encode(String data) {
		int numParityBits = numParityCalc(data.length());

		String encoded = "";

		int i = 1;
		int j = 1;

		while (i <= data.length() + numParityBits) {
			if (isPower2(i)) {
				encoded += '0';
			} else {
				encoded += data.charAt(j - 1);
				j++;
			}
			i++;
		}

		i = 1;
		j = 1;

		char[] encodedArray = encoded.toCharArray();

		while (i <= numParityBits) {
			encodedArray[j - 1] = parityBitCalc(encoded, j);
			j = j * 2;
			i++;
		}

		encoded = "";

		for (i = 0; i < encodedArray.length; i++) {
			encoded += encodedArray[i];
		}

		return encoded;
	}

	static public String decode(String encoded) {

		int numParityBits = (int) Math.floor(Math.log(encoded.length()) / Math.log(2.0)) + 1;

		int i = 1;
		int j = 1;

		char[] encodedArray = encoded.toCharArray();

		int errPos = 0;

		String decoded = "";

		while (i <= numParityBits) {
			if (encodedArray[j - 1] != parityBitCalc(encoded, j)) {
				errPos += j;
			}

			j = j * 2;
			i++;
		}

		if (errPos != 0) {
			char err = encodedArray[errPos - 1];

			if (err == '1') {
				encodedArray[errPos - 1] = '0';
			} else {
				encodedArray[errPos - 1] = '1';
			}
		}

		for (i = 1; i <= encodedArray.length; i++) {
			if (!isPower2(i)) {
				decoded += encodedArray[i - 1];
			}
		}

		return decoded;
	}

	static private int numParityCalc(int wordLength) {

		int numParity = 0;

		while (Math.pow(2, numParity) < wordLength + numParity + 1) {
			numParity++;
		}

		return numParity;
	}

	static private char parityBitCalc(String encoded, int index) {

		int count1 = 0;

		for (int i = 1; i <= encoded.length(); i++) {
			if (!isPower2(i) && (i & index) != 0 && encoded.charAt(i - 1) == '1') {
				count1++;
			}
		}

		if (count1 % 2 == 0) {
			return '0';
		}

		return '1';
	}

	static private boolean isPower2(int value) {
		return (value & (value - 1)) == 0;
	}
}