
import org.junit.Assert;
import org.junit.Test;

public class LongestCommonSubsequenceTest {

    @Test/*then*/(expected = IllegalArgumentException.class)
    public void WhenFirstStringIsNull() {
        //given
        String firstString = null;
        String secondString = "abcde";

        //when
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(firstString, secondString);
        System.out.println(lcs.findLCS());
    }

    @Test/*then*/(expected = IllegalArgumentException.class)
    public void WhenSecondStringIsNull() {
        //given
        String firstString = "abcde";
        String secondString = null;

        //when
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(firstString, secondString);
        System.out.println(lcs.findLCS());
    }

    @Test/*then*/(expected = IllegalArgumentException.class)
    public void WhenFirstStringIsEmpty() {
        //given
        String firstString = "";
        String secondString = "abcde";

        //when
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(firstString, secondString);
        System.out.println(lcs.findLCS());
    }

    @Test/*then*/(expected = IllegalArgumentException.class)
    public void WhenSecondStringIsEmpty() {
        //given
        String firstString = "abcde";
        String secondString = "";

        //when
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(firstString, secondString);
        System.out.println(lcs.findLCS());
    }

    @Test
    public void CompletelyDifferentStrings() {
        //given
        LongestCommonSubsequence lcs = new LongestCommonSubsequence("abcdefghijklmn", "opqrstuvwxyz");

        //then
        Assert.assertEquals("", lcs.findLCS());
    }

    @Test
    public void FindLCSKnowingTheResult() {
        //given
        LongestCommonSubsequence lcs = new LongestCommonSubsequence("kota albo psy", "ala ma kota i psa");

        //when-then
        Assert.assertEquals("kota  ps", lcs.findLCS());
        System.out.println("MATRIX FOR TESTING THE OVERALL LOOK!");
        lcs.display();
    }

    @Test
    public void DisplayWithEscapes() {
        //given
        LongestCommonSubsequence withEscapes = new LongestCommonSubsequence("\n\t\r\f\b\"'", "\n\t\r\f\b\"'");

        //then
        System.out.println("MATRIX FOR TESTING THE DISPLAY OF ESCAPES!");
        withEscapes.display();
    }

    @Test
    public void DisplayWithDecimals() {
        //given
        LongestCommonSubsequence lcs = new LongestCommonSubsequence("abcdefghijklmn", "abcdefghijklmn");

        //then
        System.out.println("MATRIX FOR TESTING THE DISPLAY OF DECIMALS!");
        lcs.display();
    }
}
