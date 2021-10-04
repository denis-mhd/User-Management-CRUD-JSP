package com.user.management.model.service;

import java.time.LocalDate;


public class UserServiceModel {
		
		private long id;
		private String firstName;
		private String lastName;
		private LocalDate dateOfBirth;
		private int phoneNumber;
		private String emailAddress;
	
		public UserServiceModel() {
		}
		
		public UserServiceModel(String firstName, String lastName, LocalDate dateOfBirth, int phoneNumber, String emailAddress) {
			this.firstName = firstName;
			this.lastName = lastName;
			this.dateOfBirth = dateOfBirth;
			this.phoneNumber = phoneNumber;
			this.emailAddress = emailAddress;
		}
		
		
		public UserServiceModel(long id, String firstName, String lastName, LocalDate dateOfBirth, int phoneNumber, String emailAddress) {
			this.id = id;
			this.firstName = firstName;
			this.lastName = lastName;
			this.dateOfBirth = dateOfBirth;
			this.phoneNumber = phoneNumber;
			this.emailAddress = emailAddress;
		}
		
		
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public LocalDate getDateOfBirth() {
			return dateOfBirth;
		}
		public void setDateOfBirth(LocalDate dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
		}
		public int getPhoneNumber() {
			return phoneNumber;
		}
		public void setPhoneNumber(int phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
		public String getEmailAddress() {
			return emailAddress;
		}
		public void setEmailAddress(String emailAddress) {
			this.emailAddress = emailAddress;
		}
		
}
