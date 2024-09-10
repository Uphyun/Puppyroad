package com.puppyroad.app.petstarprofile.service;

import lombok.Data;

@Data
public class PetStarProfileVO {
	private String profilePicture;
	private int gender;
	private String personality;
	private String memberCode;
	private String nickname;
}
