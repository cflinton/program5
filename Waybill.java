
public class Waybill {
	private int waybillNumber;
	private String toAddress;
	private String fromAddress;
	
	public Waybill() {
		waybillNumber = 0;
		toAddress = null;
		fromAddress = null;
	}
	
	public Waybill(int number, String receiver, String sender) {
		waybillNumber = number;
		toAddress = receiver;
		fromAddress = sender;
	}
	
	public int getNumber() {
		return waybillNumber;
	}
	
	public String getDestination() {
		return toAddress;
	}
	
	public String getSender() {
		return fromAddress;
	}
	
	public String toString() {
		String output = "";
		output = output + "Waybill: " + waybillNumber + "\n";
		output = output + "Sender: " + fromAddress + "\n";
		output = output + "Destination: " + toAddress;
		return output;
	}
}
