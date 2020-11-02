package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TrelloValidatorTest {

    @Autowired
    private TrelloValidator trelloValidator;

    @Test
    public void shouldValidateTrelloBoards(){
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "my_list", false));
        TrelloBoard testTrelloBoard = new TrelloBoard("1", "TEST", trelloLists);
        TrelloBoard trelloBoard = new TrelloBoard("1", "board", trelloLists);
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        trelloBoardList.add(testTrelloBoard);
        trelloBoardList.add(trelloBoard);
        //When
        List<TrelloBoard> listAfterValidation = trelloValidator.validateTrelloBoards(trelloBoardList);
        //Then
        assertEquals(1, listAfterValidation.size());
    }

}

