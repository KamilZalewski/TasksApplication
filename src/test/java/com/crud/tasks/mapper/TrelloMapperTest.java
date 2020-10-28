package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TrelloMapperTest {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToBoards() {
        //Given
        List<TrelloListDto> testingList = new ArrayList<>();
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("1", "Testing board", testingList);
        List<TrelloBoardDto> testingListTrelloBoards = new ArrayList<>();
        testingListTrelloBoards.add(trelloBoardDto);
        //When
        int size = trelloMapper.mapToBoards(testingListTrelloBoards).size();
        //Then
        Assert.assertEquals(1, size);
    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        List<TrelloList> listOfTrelloLists = new ArrayList<>();
        TrelloBoard trelloBoard1 = new TrelloBoard("1", "Testing Trello Board", listOfTrelloLists);
        TrelloBoard trelloBoard2 = new TrelloBoard("2", "Testing Trello Board", listOfTrelloLists);
        List<TrelloBoard> listOfTrelloBoards = new ArrayList<>();
        listOfTrelloBoards.add(trelloBoard1);
        listOfTrelloBoards.add(trelloBoard2);
        //When
        int size = trelloMapper.mapToBoardsDto(listOfTrelloBoards).size();
        //Then
        Assert.assertEquals(2, size);
    }

    @Test
    public void testMapToList() {
        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "Testing TrelloListDto", false);
        TrelloListDto trelloListDto2 = new TrelloListDto("2", "Testing TrelloListDto", false);
        List<TrelloListDto> listOfTrelloListDto = new ArrayList<>();
        listOfTrelloListDto.add(trelloListDto1);
        listOfTrelloListDto.add(trelloListDto2);
        //When
        int size = trelloMapper.mapToList(listOfTrelloListDto).size();
        //Then
        Assert.assertEquals(2, size);
    }

    @Test
    public void testMapToListDto() {
        //Given
        TrelloList trelloList1 = new TrelloList("1", "Testing Trello List", false);
        TrelloList trelloList2 = new TrelloList("2", "Testing Trello List", false);
        TrelloList trelloList3 = new TrelloList("3", "Testing Trello List", false);
        List<TrelloList> listOfTrelloLists = new ArrayList<>();
        listOfTrelloLists.add(trelloList1);
        listOfTrelloLists.add(trelloList2);
        listOfTrelloLists.add(trelloList3);
        //When
        int size = trelloMapper.mapToListDto(listOfTrelloLists).size();
        //Then
        Assert.assertEquals(3, size);
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard(
                "Testing name", "Testing description", "top", "1");
        //When
        String name = trelloMapper.mapToCardDto(trelloCard).getName();
        String description = trelloMapper.mapToCardDto(trelloCard).getDescription();
        String pos = trelloMapper.mapToCardDto(trelloCard).getPos();
        String listId = trelloMapper.mapToCardDto(trelloCard).getListId();
        //Then
        Assert.assertEquals("Testing name", name);
        Assert.assertEquals("Testing description", description);
        Assert.assertEquals("top", pos);
        Assert.assertEquals("1", listId);
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto(
                "Testing name", "Testing description", "top", "1");
        //When
        String name = trelloMapper.mapToCard(trelloCardDto).getName();
        String description = trelloMapper.mapToCard(trelloCardDto).getDescription();
        String pos = trelloMapper.mapToCard(trelloCardDto).getPos();
        String listId = trelloMapper.mapToCard(trelloCardDto).getListId();
        //Then
        Assert.assertEquals("Testing name", name);
        Assert.assertEquals("Testing description", description);
        Assert.assertEquals("top", pos);
        Assert.assertEquals("1", listId);
    }
}
