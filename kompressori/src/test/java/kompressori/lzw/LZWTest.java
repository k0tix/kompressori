package kompressori.lzw;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for Lempel-Ziv-Welch
 */
public class LZWTest {
    private LZW lzw;

    @Before
    public void setUp() {
        this.lzw = new LZW();
    }

    @Test
    public void noDataLoss() {
        byte[] input = "This is again just some test data".getBytes();
        byte[] encodedInput = this.lzw.encode(input);
        byte[] decodedInput = this.lzw.decode(encodedInput);

        assertArrayEquals(input, decodedInput);
    }

    @Test
    public void noDataLostLongInput() {
        this.lzw = new LZW(257);
        String input = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec in justo vestibulum, gravida nisl a, laoreet elit. Aliquam venenatis, dui sed sollicitudin mattis, tortor arcu cursus est, non congue metus lectus sit amet ante. Praesent tempus sit amet massa et luctus. Donec accumsan erat vel mauris laoreet, id pulvinar leo finibus. Quisque molestie interdum ipsum quis luctus. Nulla egestas neque eu nisl suscipit aliquam. Donec pulvinar mattis quam, in condimentum eros condimentum eget. Nam condimentum est orci, faucibus elementum quam laoreet in."
                + "Quisque sit amet lectus pharetra, egestas enim id, faucibus eros. Ut felis enim, egestas ac lacinia non, bibendum nec libero. Donec neque magna, ultricies eget urna nec, efficitur commodo nisi. Vivamus vehicula risus a pharetra pellentesque. Sed nec orci eu turpis faucibus imperdiet eget nec nisl. Mauris commodo a leo at sollicitudin. Etiam feugiat felis eget diam convallis pulvinar. Praesent quis posuere odio, id auctor lacus. Praesent aliquet orci vulputate massa scelerisque accumsan. Curabitur non viverra leo."
                + "Quisque ullamcorper lorem elit, ut varius nibh porta nec. In ac auctor nulla. Praesent vel neque eget turpis suscipit scelerisque. Morbi sed tempus tortor. Etiam nulla nunc, accumsan at tellus ut, ultrices finibus sem. Suspendisse et augue et justo fermentum vulputate. Nullam quis augue vestibulum, ornare lectus a, ultrices magna."
                + "Aenean nec dolor turpis. Mauris placerat turpis dui, a bibendum leo rutrum non. Donec mollis congue mi, ut ullamcorper elit venenatis vitae. Vestibulum augue leo, sollicitudin ac justo nec, finibus euismod urna. Nulla aliquam non augue in euismod. Praesent ultrices sodales odio, non tincidunt elit rutrum et. Phasellus nec ullamcorper enim, tristique varius felis. Integer ornare lorem dui, sed varius nisi consequat nec. Aliquam dapibus ante lectus, quis suscipit elit malesuada ac. Cras non mauris augue. Vivamus suscipit, libero ornare molestie efficitur, ante nibh scelerisque erat, nec tristique mauris dolor vulputate magna."
                + "Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Praesent eget urna nec urna rhoncus eleifend. Duis vel quam libero. Donec nec quam mauris. Pellentesque blandit vestibulum tristique. Aliquam lorem nisi, consectetur volutpat finibus nec, viverra nec nisi. Sed justo nunc, pretium in neque vel, semper suscipit magna. Aliquam id sollicitudin ligula. Proin sed elit ut neque ultricies tristique fringilla sit amet magna."
                + "Quisque sit amet lectus pharetra, egestas enim id, faucibus eros. Ut felis enim, egestas ac lacinia non, bibendum nec libero. Donec neque magna, ultricies eget urna nec, efficitur commodo nisi. Vivamus vehicula risus a pharetra pellentesque. Sed nec orci eu turpis faucibus imperdiet eget nec nisl. Mauris commodo a leo at sollicitudin. Etiam feugiat felis eget diam convallis pulvinar. Praesent quis posuere odio, id auctor lacus. Praesent aliquet orci vulputate massa scelerisque accumsan. Curabitur non viverra leo."
                + "Quisque ullamcorper lorem elit, ut varius nibh porta nec. In ac auctor nulla. Praesent vel neque eget turpis suscipit scelerisque. Morbi sed tempus tortor. Etiam nulla nunc, accumsan at tellus ut, ultrices finibus sem. Suspendisse et augue et justo fermentum vulputate. Nullam quis augue vestibulum, ornare lectus a, ultrices magna."
                + "Aenean nec dolor turpis. Mauris placerat turpis dui, a bibendum leo rutrum non. Donec mollis congue mi, ut ullamcorper elit venenatis vitae. Vestibulum augue leo, sollicitudin ac justo nec, finibus euismod urna. Nulla aliquam non augue in euismod. Praesent ultrices sodales odio, non tincidunt elit rutrum et. Phasellus nec ullamcorper enim, tristique varius felis. Integer ornare lorem dui, sed varius nisi consequat nec. Aliquam dapibus ante lectus, quis suscipit elit malesuada ac. Cras non mauris augue. Vivamus suscipit, libero ornare molestie efficitur, ante nibh scelerisque erat, nec tristique mauris dolor vulputate magna."
                + "Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Praesent eget urna nec urna rhoncus eleifend. Duis vel quam libero. Donec nec quam mauris. Pellentesque blandit vestibulum tristique. Aliquam lorem nisi, consectetur volutpat finibus nec, viverra nec nisi. Sed justo nunc, pretium in neque vel, semper suscipit magna. Aliquam id sollicitudin ligula. Proin sed elit ut neque ultricies tristique fringilla sit amet magna."
                + "Quisque sit amet lectus pharetra, egestas enim id, faucibus eros. Ut felis enim, egestas ac lacinia non, bibendum nec libero. Donec neque magna, ultricies eget urna nec, efficitur commodo nisi. Vivamus vehicula risus a pharetra pellentesque. Sed nec orci eu turpis faucibus imperdiet eget nec nisl. Mauris commodo a leo at sollicitudin. Etiam feugiat felis eget diam convallis pulvinar. Praesent quis posuere odio, id auctor lacus. Praesent aliquet orci vulputate massa scelerisque accumsan. Curabitur non viverra leo."
                + "Quisque ullamcorper lorem elit, ut varius nibh porta nec. In ac auctor nulla. Praesent vel neque eget turpis suscipit scelerisque. Morbi sed tempus tortor. Etiam nulla nunc, accumsan at tellus ut, ultrices finibus sem. Suspendisse et augue et justo fermentum vulputate. Nullam quis augue vestibulum, ornare lectus a, ultrices magna."
                + "Aenean nec dolor turpis. Mauris placerat turpis dui, a bibendum leo rutrum non. Donec mollis congue mi, ut ullamcorper elit venenatis vitae. Vestibulum augue leo, sollicitudin ac justo nec, finibus euismod urna. Nulla aliquam non augue in euismod. Praesent ultrices sodales odio, non tincidunt elit rutrum et. Phasellus nec ullamcorper enim, tristique varius felis. Integer ornare lorem dui, sed varius nisi consequat nec. Aliquam dapibus ante lectus, quis suscipit elit malesuada ac. Cras non mauris augue. Vivamus suscipit, libero ornare molestie efficitur, ante nibh scelerisque erat, nec tristique mauris dolor vulputate magna."
                + "Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Praesent eget urna nec urna rhoncus eleifend. Duis vel quam libero. Donec nec quam mauris. Pellentesque blandit vestibulum tristique. Aliquam lorem nisi, consectetur volutpat finibus nec, viverra nec nisi. Sed justo nunc, pretium in neque vel, semper suscipit magna. Aliquam id sollicitudin ligula. Proin sed elit ut neque ultricies tristique fringilla sit amet magna.";

        byte[] byteInput = input.getBytes();
        byte[] encodedInput = this.lzw.encode(byteInput);
        byte[] decodedInput = this.lzw.decode(encodedInput);

        assertArrayEquals(byteInput, decodedInput);
    }
}