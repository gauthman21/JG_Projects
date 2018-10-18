package com.syntel.pojo.copy;

import java.util.List;

import com.syntel.dao.DiscountsDAOImpl;

public class Discounts {

	public Discounts() {
		
	}
	private int discountid, packageid, percentoff;
	private String extras;
	private String discountdate;
	@Override
	public String toString() {
		return "Discounts [discountid=" + discountid + ", packageid="
				+ packageid + ", percentoff=" + percentoff + ", extras="
				+ extras + ", discountdate=" + discountdate + "]";
	}
	public Discounts(int discountid, String discountdate, int packageid, int percentoff,
			String extras) {
		super();
		this.discountid = discountid;
		this.packageid = packageid;
		this.percentoff = percentoff;
		this.extras = extras;
		this.discountdate = discountdate;
	}
	public int getdiscountid() {
		return discountid;
	}
	public void setdiscountid(int discountid) {
		this.discountid = discountid;
	}
	public int getpackageid() {
		return packageid;
	}
	public void setpackageid(int packageid) {
		this.packageid = packageid;
	}
	public int getpercentoff() {
		return percentoff;
	}
	public void setpercentoff(int percentoff) {
		this.percentoff = percentoff;
	}
	public String getextras() {
		return extras;
	}
	public void setextras(String extras) {
		this.extras = extras;
	}
	
	public List<Discounts> getAll(){
		return new DiscountsDAOImpl().getAllDiscounts();
	}
	public void addDiscount(){
		Discounts d = new Discounts(this.discountid, this.discountdate, this.packageid, this.percentoff,
				this.extras);
		new DiscountsDAOImpl().insertDiscounts(d);
	}
	public void updateDiscount(){
		Discounts d = new Discounts(this.discountid, this.discountdate, this.packageid, this.percentoff,
				this.extras);
		new DiscountsDAOImpl().updateDiscounts(d);
	}
	public void deleteDiscount(int id){
		new DiscountsDAOImpl().deleteDiscounts(new DiscountsDAOImpl().getDiscounts(id));
	}
	public Discounts getDiscount(int id){
		return new DiscountsDAOImpl().getDiscounts(id);
	}
	public String getdiscountdate() {
		return discountdate;
	}
	public void setdiscountdate(String discountdate) {
		this.discountdate = discountdate;
	}
}
