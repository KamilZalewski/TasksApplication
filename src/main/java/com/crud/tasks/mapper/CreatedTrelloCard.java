package com.crud.tasks.mapper;

import com.crud.tasks.domain.AttachmentByTypeDto;
import com.crud.tasks.domain.BadgesDto;
import com.crud.tasks.domain.TrelloDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatedTrelloCard {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("shortUrl")
    private String shortUrl;

    @JsonProperty("badges")
    private BadgesDto badges;

    @JsonProperty("attachmentByType")
    private AttachmentByTypeDto attachmentByType;

    @JsonProperty("trello")
    private TrelloDto trello;





}
