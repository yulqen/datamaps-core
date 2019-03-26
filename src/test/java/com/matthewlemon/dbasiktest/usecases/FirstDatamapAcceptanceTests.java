package com.matthewlemon.dbasiktest.usecases;

import com.matthewlemon.dbasiktest.entities.Datamap;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

// this is an alternative to the Fitness test
// watching Outside In TDD (https://www.youtube.com/watch?v=XHnuMjah6ps&t=329sa)

@RunWith(MockitoJUnitRunner.class)
public class FirstDatamapAcceptanceTests {

    private Datamap datamap;

    @Before
    public void initialise() {
        datamap = new Datamap("Test Datamap");
    }


    @Test
    public void createDatamapWithDatamapLines() {
        // TODO WTF goes here?? Need some kind of output.
        verify(datamap).getName().equals("Test Datamap");
    }
}
