package com.jawang.board.qna;
import com.jawang.board.BoardDTO;

public class QnaDTO extends BoardDTO {
	

	private String ref;
	private int step;
	private String depth;
	
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public String getDepth() {
		return depth;
	}
	public void setDepth(String depth) {
		this.depth = depth;
	}
	
	

}
