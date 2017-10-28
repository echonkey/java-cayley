package cayley.http.model;

public class Quad {
	private String subject = null;	//主体
	private String predicate = null;	//关系
	private String object = null;	//客体
	private String label = null;	//标签
	
	public Quad() {
		super();
	}

	public Quad(String subject, String predicate, String object, String label) {
		super();
		this.subject = subject;
		this.predicate = predicate;
		this.object = object;
		this.label = label;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPredicate() {
		return predicate;
	}

	public void setPredicate(String predicate) {
		this.predicate = predicate;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return "Quad [subject=" + subject + ", predicate=" + predicate + ", object=" + object + ", label=" + label + "]";
	}
	
}
