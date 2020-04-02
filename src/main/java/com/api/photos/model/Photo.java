package com.api.photos.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Photo {
	private int albumId;
	private int id;
	private String title;
	private String url;
	private String thumbnailUrl;
}
