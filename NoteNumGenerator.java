package carrier;

public class NoteNumGenerator {
	
	public static String numGenerator(Carrier carrier) {
		StringBuffer consignmentNumBuffer = new StringBuffer();
		StringBuffer indexBuffer = new StringBuffer();
		int fillLength = 0;
		char[] indexArray;
		try {
			int index = carrier.getLastUsedIndex() + 1;
			int rangeStart = carrier.getRangeStart();
			int rangeEnd = carrier.getRangeEnd();
			int digits = carrier.getDigits();
			int odd=0, even=0, total, checksum;
			
			if(index > rangeEnd || index < rangeStart) {
				return "index out of range: " + index;
			}else {//legal length
				fillLength = digits - String.valueOf(index).length();
				consignmentNumBuffer.append(carrier.getCarrierName()).append(carrier.getAccountNumber());
				for(int i = 0; i < fillLength; i++) {
					indexBuffer.append('0');
				}
				indexBuffer.append(index);
				consignmentNumBuffer.append(indexBuffer);
				indexArray = indexBuffer.toString().toCharArray();
				int j = indexArray.length - 1;
				while(j > 0) {
					//from the right starting at the first element
					odd += Integer.parseInt(String.valueOf((indexArray[j])));
					//from the right starting at the second element
					even += Integer.parseInt(String.valueOf((indexArray[j-1])));
					j = j - 2;
				}
				//if the array has odd elements, add first element from left
				if(j % 2 == 0) {
					odd += Integer.parseInt(String.valueOf((indexArray[0])));
				}
				total = odd * 3 + even * 7;				
				checksum = 10 - total % 10;
				consignmentNumBuffer.append(checksum);
				return consignmentNumBuffer.toString();
			}
		}catch (Exception e) {			
			System.out.println("Exception thrown  :" + e);
			return "error occur!";
		}		
	}
	
	
	public static void main(String[] args) {
		Carrier carrier = new Carrier();
		carrier.setAccountNumber("123ABC");
		carrier.setCarrierName("FMCC");
		carrier.setDigits(10);
		carrier.setLastUsedIndex(19604);
		carrier.setRangeEnd(20000);
		carrier.setRangeStart(19000);
		String result = numGenerator(carrier);
		System.out.println(result);
	}
}
