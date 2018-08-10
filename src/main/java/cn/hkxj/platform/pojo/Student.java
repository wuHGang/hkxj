package cn.hkxj.platform.pojo;

import com.google.common.base.MoreObjects;

import java.util.Date;

/**
 * 对应的model
 */
public class Student {
    /**
     * 
     */
    private Integer id;
    /**
     * 
     */
    private Integer account;
    /**
     * 
     */
    private String password;
    /**
     * 
     */
    private String name;
    /**
     * 
     */
    private String sex;
    /**
     * 
     */
    private String ethnic;
    /**
     * 
     */
    private String academy;
    /**
     * 
     */
    private String major;
    /**
     * 
     */
    private String classname;
    /**
     * 
     */
    private Boolean isCorrect;
    /**
     * 
     */
    private Date gmtCreate;
    /**
     * 
     */
    private Date gmtModified;

	public Student() {
	}

	public Student(Integer id, Integer account, String password, String name, String sex, String ethnic, String academy, String major, String classname, Boolean isCorrect, Date gmtCreate, Date gmtModified) {
		this.id = id;
		this.account = account;
		this.password = password;
		this.name = name;
		this.sex = sex;
		this.ethnic = ethnic;
		this.academy = academy;
		this.major = major;
		this.classname = classname;
		this.isCorrect = isCorrect;
		this.gmtCreate = gmtCreate;
		this.gmtModified = gmtModified;
	}

	public static StudentBuilder builder() {
		return new StudentBuilder();
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getEthnic() {
        return ethnic;
    }

    public void setEthnic(String ethnic) {
        this.ethnic = ethnic;
    }
    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }
    public Boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Boolean isCorrect) {
        this.isCorrect = isCorrect;
    }
    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }


	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("id", id)
				.add("account", account)
				.add("password", password)
				.add("name", name)
				.add("sex", sex)
				.add("ethnic", ethnic)
				.add("academy", academy)
				.add("major", major)
				.add("classname", classname)
				.add("isCorrect", isCorrect)
				.add("gmtCreate", gmtCreate)
				.add("gmtModified", gmtModified)
				.toString();
	}

	public static class StudentBuilder {
		private Integer id;
		private Integer account;
		private String password;
		private String name;
		private String sex;
		private String ethnic;
		private String academy;
		private String major;
		private String classname;
		private Boolean isCorrect;
		private Date gmtCreate;
		private Date gmtModified;

		StudentBuilder() {
		}

		public StudentBuilder id(Integer id) {
			this.id = id;
			return this;
		}

		public StudentBuilder account(Integer account) {
			this.account = account;
			return this;
		}

		public StudentBuilder password(String password) {
			this.password = password;
			return this;
		}

		public StudentBuilder name(String name) {
			this.name = name;
			return this;
		}

		public StudentBuilder sex(String sex) {
			this.sex = sex;
			return this;
		}

		public StudentBuilder ethnic(String ethnic) {
			this.ethnic = ethnic;
			return this;
		}

		public StudentBuilder academy(String academy) {
			this.academy = academy;
			return this;
		}

		public StudentBuilder major(String major) {
			this.major = major;
			return this;
		}

		public StudentBuilder classname(String classname) {
			this.classname = classname;
			return this;
		}

		public StudentBuilder isCorrect(Boolean isCorrect) {
			this.isCorrect = isCorrect;
			return this;
		}

		public StudentBuilder gmtCreate(Date gmtCreate) {
			this.gmtCreate = gmtCreate;
			return this;
		}

		public StudentBuilder gmtModified(Date gmtModified) {
			this.gmtModified = gmtModified;
			return this;
		}

		public Student build() {
			return new Student(id, account, password, name, sex, ethnic, academy, major, classname, isCorrect, gmtCreate, gmtModified);
		}

		public String toString() {
			return "Student.StudentBuilder(id=" + this.id + ", account=" + this.account + ", password=" + this.password + ", name=" + this.name + ", sex=" + this.sex + ", ethnic=" + this.ethnic + ", academy=" + this.academy + ", major=" + this.major + ", classname=" + this.classname + ", isCorrect=" + this.isCorrect + ", gmtCreate=" + this.gmtCreate + ", gmtModified=" + this.gmtModified + ")";
		}
	}
}
