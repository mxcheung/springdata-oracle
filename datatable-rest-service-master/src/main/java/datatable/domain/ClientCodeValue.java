package datatable.domain;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class ClientCodeValue {

    @Id
    @SequenceGenerator(name = "generator", sequenceName = "ClientCodeValue_PK_ID")
    @GeneratedValue(strategy = SEQUENCE, generator = "generator")
    @Column(name = "ID")
    private long id;
    
    
    @JoinColumn(name = "Client_Code_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ClientCode clientCode;

    private String codeKey;
    private String codeValue;

    public ClientCodeValue() {}
    
	public ClientCode getClientCode() {
		return clientCode;
	}


	public void setClientCode(ClientCode clientCode) {
		this.clientCode = clientCode;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	

	public String getCodeKey() {
		return codeKey;
	}

	public void setCodeKey(String codeKey) {
		this.codeKey = codeKey;
	}

	public String getCodeValue() {
		return codeValue;
	}

	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}




}

