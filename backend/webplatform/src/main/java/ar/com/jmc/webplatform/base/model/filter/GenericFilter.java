package ar.com.jmc.webplatform.base.model.filter;

public abstract class GenericFilter<T> {

	private String orderField;
	private Boolean orderDescending;
	private Integer pagina;
	private Integer registrosPorPagina;
	
	public String getOrderField() {
		return orderField;
	}
	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}
	public Boolean getOrderDescending() {
		return orderDescending;
	}
	public void setOrderDescending(Boolean orderDescending) {
		this.orderDescending = orderDescending;
	}
	public Integer getPagina() {
		return pagina;
	}
	public void setPagina(Integer pagina) {
		this.pagina = pagina;
	}
	public Integer getRegistrosPorPagina() {
		return registrosPorPagina;
	}
	public void setRegistrosPorPagina(Integer registrosPorPagina) {
		this.registrosPorPagina = registrosPorPagina;
	}
}
