package com.syntel.pojo.copy;

import java.util.List;

import com.syntel.dao.CustomerOrderDAOImpl;
import com.syntel.dao.OrderDetailsDAOImpl;

public class CustomerOrder {

	public CustomerOrder() {

	}

	private int orderid, area, cid, payid;
	private String odays, oaddress, paymentstatus, orderstatus, otime, odate,
			oenddate;

	@Override
	public String toString() {
		return "CustomerOrder [OrderID=" + orderid + ", Area=" + area
				+ ", CID=" + cid + ", PayID=" + payid + ", ODays=" + odays
				+ ", OAddress=" + oaddress + ", PaymentStatus=" + paymentstatus
				+ ", OrderStatus=" + orderstatus + ", ODate=" + odate
				+ ", OEndDate=" + oenddate + ", OTime=" + otime + "]";
	}

	public CustomerOrder(int orderid, String odate, String otime,
			String oenddate, String odays, int area, String oaddress, int cid,
			int payid, String paymentstatus, String orderstatus) {
		super();
		this.orderid = orderid;
		this.area = area;
		this.cid = cid;
		this.payid = payid;
		this.odays = odays;
		this.oaddress = oaddress;
		this.paymentstatus = paymentstatus;
		this.orderstatus = orderstatus;
		this.otime = otime;
		this.odate = odate;
		this.oenddate = oenddate;
	}

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getPayid() {
		return payid;
	}

	public void setPayid(int payid) {
		this.payid = payid;
	}

	public String getOdays() {
		return odays;
	}

	public void setOdays(String odays) {
		this.odays = odays;
	}

	public String getOaddress() {
		return oaddress;
	}

	public void setOaddress(String oaddress) {
		this.oaddress = oaddress;
	}

	public String getPaymentstatus() {
		return paymentstatus;
	}

	public void setPaymentstatus(String paymentstatus) {
		this.paymentstatus = paymentstatus;
	}

	public String getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}

	public String getOtime() {
		return otime;
	}

	public void setOtime(String otime) {
		this.otime = otime;
	}

	public String getOdate() {
		return odate;
	}

	public void setOdate(String odate) {
		this.odate = odate;
	}

	public String getOenddate() {
		return oenddate;
	}

	public void setOenddate(String oenddate) {
		this.oenddate = oenddate;
	}

	public List<CustomerOrder> getAll() {
		return new CustomerOrderDAOImpl().getAllCustomerOrder();
	}

	public void updateOrder(){
		CustomerOrder co = new CustomerOrder(this.orderid, this.odate, this.otime,
				this.oenddate, this.odays, this.area, this.oaddress, this.cid,
				this.payid, this.paymentstatus, this.orderstatus);
		new CustomerOrderDAOImpl().updateCustomerOrder(co);
	}

	public void deleteOrder(int co) {
		new OrderDetailsDAOImpl().deleteForOrder(co);
		CustomerOrder c = new CustomerOrderDAOImpl().getCustomerOrderbyID(co);
		new CustomerOrderDAOImpl().deleteCustomerOrder(c);
	}

	public CustomerOrder getOrder(int id) {
		return new CustomerOrderDAOImpl().getCustomerOrderbyID(id);
	}
	public int numOfOrder(){
		int totalOrders = new CustomerOrderDAOImpl().getTotalOrders();
		return totalOrders;
	}
	
	
}
