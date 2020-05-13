package com.ariadnext.idcheck.exercise.dto;

public class AnalysisResult {
	private String uid = null;
	
	private HolderDetail holderDetail;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public HolderDetail getHolderDetail() {
		return holderDetail;
	}

	public void setHolderDetail(HolderDetail holderDetail) {
		this.holderDetail = holderDetail;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((holderDetail == null) ? 0 : holderDetail.hashCode());
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
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
		AnalysisResult other = (AnalysisResult) obj;
		if (holderDetail == null) {
			if (other.holderDetail != null)
				return false;
		} else if (!holderDetail.equals(other.holderDetail))
			return false;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AnalysisResult [uid=" + uid + ", holderDetail=" + holderDetail + "]";
	}
}
