package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class TrelloFacadeTest {

    @Autowired
    private TrelloMapper trelloMapper;
    @Test
    public void testMapToBoards() {
        //Given
        List<TrelloListDto> testingList = new ArrayList<>();
        TrelloListDto trelloListDto = new TrelloListDto("1","TestingList", false);
        TrelloCardDto card1 = new TrelloCardDto("test name1", "test description1", "top","1");
        TrelloCardDto card2 = new TrelloCardDto("test name2", "test description2", "top","1");
        testingList.add(trelloListDto);
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("1","Testing board", testingList);
        List<TrelloBoardDto> testingListTrelloBoards = new ArrayList<>();
        testingListTrelloBoards.add(trelloBoardDto);
        //When
        int size = trelloMapper.mapToBoards(testingListTrelloBoards).size();
        //Then
        Assert.assertEquals(1, size);
    }
}
