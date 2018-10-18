 package com.syntel.pojo.copy;

public class OrderDetails {
	
	private int OrderDetailsID, OrderID, PackageID;

	@Override
	public String toString() {
		return "OrderDetails [OrderDetailsID=" + OrderDetailsID + ", OrderID="
				+ OrderID + ", PackageID=" + PackageID + "]";
	}

	public OrderDetails(int orderDetailsID, int orderID, int packageID) {
		super();
		OrderDetailsID = orderDetailsID;
		OrderID = orderID;
		PackageID = packageID;
	}

	public int getOrderDetailsID() {
		return OrderDetailsID;
	}

	public void setOrderDetailsID(int orderDetailsID) {
		OrderDetailsID = orderDetailsID;
	}

	public int getOrderID() {
		return OrderID;
	}

	public void setOrderID(int orderID) {
		OrderID = orderID;
	}

	public int getPackageID() {
		return PackageID;
	}

	public void setPackageID(int packageID) {
		PackageID = packageID;
	}	
}
