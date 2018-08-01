public class StringsAndArrays {
	public static void main(String args[]) {
		StringsAndArrays saa = new StringsAndArrays();
		saa.checkDuplicate("abcde");
		saa.checkDuplicate("abcdeb");
		saa.checkPermutation("abcde", "eabcd");
		saa.checkPermutation("abcde", "eafcd");
		saa.replaceSpace("abcd cde cd cdee   ");
		saa.compressString("abcdcccccdddeeefff");
		saa.compressString("abcdee");
		saa.compressString("abcde");
	}
	// 1.1
	public boolean checkDuplicate(String str) {
		boolean[] checks = new boolean[256];
		for(int i=0; i<str.length(); i++) {
			char c = str.charAt(i);
			if(checks[c]) {
				System.out.println("String "+ str+" DO contains duplicate char!");
				return true;
			} else {
				checks[c] = true;
			}
		}
		System.out.println("String "+ str+" does NOT contain duplicate char!");
		return false;
	}
	// 1.3
	public boolean checkPermutation(String str1, String str2) {
		int [] charCount = new int[256];
		
		if(str1.length() != str2.length()) {
			System.out.println("String 1 >"+str1+"< and String 2 >"+str2+"< are NOT permutation!");
			return false;
		}
		else {
			for(int i=0; i<str1.length(); i++) {
				char c = str1.charAt(i);
				charCount[c] ++;
			}
			for(int i=0; i<str2.length(); i++) {
				char c = str2.charAt(i);
				charCount[c] --;
				if(charCount[c] < 0) {
					System.out.println("String 1 >"+str1+"< and String 2 >"+str2+"< are NOT permutation!");
					return false;
				}
			}
			System.out.println("String 1 >"+str1+"< and String 2 >"+str2+"< ARE permutation!");
			return true;
		}
	} 
	// 1.4
	public String replaceSpace(String str) {
		char[] charArray = str.toCharArray();
		int numberOfSpace = 0;
		for(int i=0; i<charArray.length; i++) {
			if(charArray[i] == ' ')
				numberOfSpace ++;
		}
		char[] newCharArray = new char[charArray.length + 2 * numberOfSpace];
		for(int i=0, j=0; i<charArray.length && j<newCharArray.length; i++, j++) {
			if(charArray[i] == ' ') {
				newCharArray[j++] = '%';
				newCharArray[j++] = '2';
				newCharArray[j] = '0';
			} else
				newCharArray[j] = charArray[i];
		}
		System.out.println("Original String >"+str+"< and New String >"+String.valueOf(newCharArray)+"<!");
		return String.valueOf(newCharArray);
	}
	// 1.5
	public String compressString(String str) {
		if(str.length() <= 2) {
			System.out.println("Original String >"+str+"< and New String >"+str+"<!");
			return str;
		}
		char[] os = str.toCharArray();
		char[] ns = new char[os.length];
		
		int np=0, op=0;
		while(op < os.length && np < ns.length - 1) {
			if(ns[np] == '\0') {
				ns[np] = os[op];
				ns[np+1] = '1';
				op++;
			} else {
				if(ns[np] == os[op]) {
					ns[np+1] ++;
					op++;
				} else {
					np = np + 2;
				}
			}
		}
		if(np >= ns.length - 2) {
			System.out.println("Original String >"+str+"< and New String >"+str+"<!");
			return str;
		}
		else {
			System.out.println("Original String >"+str+"< and New String >"+String.valueOf(ns)+"<!");
			return String.valueOf(ns);
		}
	}	
}