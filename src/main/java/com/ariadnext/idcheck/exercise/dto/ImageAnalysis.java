package com.ariadnext.idcheck.exercise.dto;

public class ImageAnalysis {
	private String frontImage; 
	private String backImage; 
	private boolean rectoImageCropped; 
	private boolean signatureImageCropped;
	private boolean faceImageCropped ;

	public String getFrontImage() {
		return frontImage;
	}

	public void setFrontImage(String frontImage) {
		this.frontImage = frontImage;
	}

	public String getBackImage() {
		return backImage;
	}

	public void setBackImage(String backImage) {
		this.backImage = backImage;
	}

	public boolean isRectoImageCropped() {
		return rectoImageCropped;
	}

	public void setRectoImageCropped(boolean rectoImageCropped) {
		this.rectoImageCropped = rectoImageCropped;
	}

	public boolean isSignatureImageCropped() {
		return signatureImageCropped;
	}

	public void setSignatureImageCropped(boolean signatureImageCropped) {
		this.signatureImageCropped = signatureImageCropped;
	}

	public boolean isFaceImageCropped() {
		return faceImageCropped;
	}

	public void setFaceImageCropped(boolean faceImageCropped) {
		this.faceImageCropped = faceImageCropped;
	}
}
