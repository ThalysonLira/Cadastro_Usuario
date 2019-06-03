package br.unitins.model;

public abstract class DefaultEntity {

	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DefaultEntity getClone() {
		try {
			return (DefaultEntity) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
}