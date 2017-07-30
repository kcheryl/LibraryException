package library.exception.beans;

public class ExceptionInfo implements Comparable<ExceptionInfo> {
	String projectName;
	String moduleName;
	String exceptionName;

	public ExceptionInfo(String projectName, String moduleName, String exception) {
		this.projectName = projectName;
		this.moduleName = moduleName;
		this.exceptionName = exception;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getException() {
		return exceptionName;
	}

	public void setException(String exception) {
		this.exceptionName = exception;
	}

	@Override
	public String toString() {
		return "ProjectInfo [" + projectName + ", " + moduleName + ", " + exceptionName + "]";
	}

	@Override
	public int compareTo(ExceptionInfo o) {
		if (this.projectName.compareTo(o.projectName) == 0) {
			if (this.moduleName.compareTo(o.moduleName) == 0) {
				return this.exceptionName.compareTo(o.exceptionName);
			}
			return this.moduleName.compareTo(o.moduleName);
		}
		return this.projectName.compareTo(o.projectName);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((exceptionName == null) ? 0 : exceptionName.hashCode());
		result = prime * result + ((moduleName == null) ? 0 : moduleName.hashCode());
		result = prime * result + ((projectName == null) ? 0 : projectName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExceptionInfo other = (ExceptionInfo) obj;
		if (exceptionName == null) {
			if (other.exceptionName != null)
				return false;
		} else if (!exceptionName.equals(other.exceptionName))
			return false;
		if (moduleName == null) {
			if (other.moduleName != null)
				return false;
		} else if (!moduleName.equals(other.moduleName))
			return false;
		if (projectName == null) {
			if (other.projectName != null)
				return false;
		} else if (!projectName.equals(other.projectName))
			return false;
		return true;
	}
}
