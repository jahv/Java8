package book.java8Lambdas.ch3.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import book.java8Lambdas.domain.Artist;

/**
 * Created by ahernandez on 10/17/16.
 */
public class StreamsTest {

    /**
     * External vs Internal iteration
     * For loop on a collection is considered an external iteration
     * Internal iterations are based on streams
     */
    @Test
    public void externalIteration() {
        //External Iteration
        long count = 0;
        for(Artist artist : allArtists()) {
            if(artist.isFrom("Liverpool")) {
                count++;
            }
        }
        Assert.assertEquals(count, 2);

        //Internal Iteration
        //Using lambda for filtering on the stream, the filter knows the stream is based on Artist, so inferred the type
        //used in the filter, no need to specify type
        count = allArtists().stream().filter(artist -> artist.isFrom("Liverpool")).count();
        Assert.assertEquals(count, 2);
    }

    /**
     * Mock a list of Artists
     * @return
     */
    private List<Artist> allArtists() {
        Set<String> memebersBeatles = new HashSet<>(Arrays.asList("Jonh Lennon","Paul McCartney", "Ringo Star", "George Harrison"));
        Set<String> memebersPinkFloyd = new HashSet<>(Arrays.asList("Roger Waters","Sid Barret"));
        Set<String> memebersRollingStones = new HashSet<>(Arrays.asList("Mick Jagger","Otro"));

        List<Artist> artistList = new ArrayList<>(Arrays.asList(
                new Artist("The Beatles", memebersBeatles, "Liverpool"),
                new Artist("PinkFloyd", memebersPinkFloyd, "UK"),
                new Artist("the Rolling Stones", memebersRollingStones, "Liverpool")
        ));

        return artistList;
    }

}
