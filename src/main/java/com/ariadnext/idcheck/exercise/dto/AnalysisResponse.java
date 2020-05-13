package com.ariadnext.idcheck.exercise.dto;

public class AnalysisResponse {
	
	private String message;
	
	private AnalysisResult analysisResult;

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public AnalysisResult getAnalysisResult() {
		return analysisResult;
	}

	public void setAnalysisResult(AnalysisResult analysisResult) {
		this.analysisResult = analysisResult;
	}

	@Override
	public String toString() {
		return "AnalysisResponse [message=" + message + ", analysisResult=" + analysisResult
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((analysisResult == null) ? 0 : analysisResult.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
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
		AnalysisResponse other = (AnalysisResponse) obj;
		if (analysisResult == null) {
			if (other.analysisResult != null)
				return false;
		} else if (!analysisResult.equals(other.analysisResult))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		return true;
	}
}
