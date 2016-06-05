package com.maxcheung;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import static javax.persistence.GenerationType.SEQUENCE;


@Entity
public class ClientCode {

    @Id
    @SequenceGenerator(name = "generator", sequenceName = "ClientCode_PK_ID")
    @GeneratedValue(strategy = SEQUENCE, generator = "generator")
    @Column(name = "ID")
    private long id;
    private String clientKey;
    private String groupKey;
    
    @OneToMany(cascade = { CascadeType.ALL }, mappedBy = "clientCode", fetch = FetchType.LAZY)
    private List<ClientCodeValue> clientCodeValues;


    protected ClientCode() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getClientKey() {
		return clientKey;
	}

	public void setClientKey(String clientKey) {
		this.clientKey = clientKey;
	}

	public String getGroupKey() {
		return groupKey;
	}

	public void setGroupKey(String groupKey) {
		this.groupKey = groupKey;
	}

	public List<ClientCodeValue> getClientCodeValues() {
		return clientCodeValues;
	}

	public void setClientCodeValues(List<ClientCodeValue> clientCodeValues) {
		this.clientCodeValues = clientCodeValues;
	}




}

