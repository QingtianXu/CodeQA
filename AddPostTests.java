public class AddPostTests {

    // Method to repeat a string multiple times
    public static String repeat(String s, int count) {
        return new String(new char[count]).replace("\0", s);
    }

    // Main method to run the tests
    public static void main(String[] args) {
        testTitleTooShort();
        testTitleTooLong();
        testTitleStartsWithNumber();
        testBodyTooShort();
        testTooFewTags();
        testValidPost();
        System.out.println("All tests passed.");
    }

    // Test for a title that is too short
    public static void testTitleTooShort() {
        Post post = new Post(1, "Short", "This is a valid body with more than 250 characters..." + repeat("A", 225), new String[]{"tag1", "tag2"}, "Easy", "Ordinary");
        assert !post.addPost() : "Test failed: Title too short should return false";
    }

    // Test for a title that is too long
    public static void testTitleTooLong() {
        String longTitle = repeat("A", 251);
        Post post = new Post(2, longTitle, "This is a valid body with more than 250 characters..." + repeat("A", 225), new String[]{"tag1", "tag2"}, "Easy", "Ordinary");
        assert !post.addPost() : "Test failed: Title too long should return false";
    }

    // Test for a title that starts with a number
    public static void testTitleStartsWithNumber() {
        Post post = new Post(3, "12345Title", "This is a valid body with more than 250 characters..." + repeat("A", 225), new String[]{"tag1", "tag2"}, "Easy", "Ordinary");
        assert !post.addPost() : "Test failed: Title starts with number should return false";
    }

    // Test for a body that is too short
    public static void testBodyTooShort() {
        String shortBody = repeat("A", 249);
        Post post = new Post(4, "Valid Title", shortBody, new String[]{"tag1", "tag2"}, "Difficult", "Highly Needed");
        assert !post.addPost() : "Test failed: Body too short should return false";
    }

    // Test for too few tags
    public static void testTooFewTags() {
        Post post = new Post(5, "Valid Title", "This is a valid body with more than 250 characters..." + repeat("A", 225), new String[]{"tag1"}, "Easy", "Ordinary");
        assert !post.addPost() : "Test failed: Too few tags should return false";
    }

    // Test for a valid post
    public static void testValidPost() {
        Post post = new Post(6, "Valid Title", "This is a valid body with more than 250 characters..." + repeat("A", 225), new String[]{"tag1", "tag2"}, "Easy", "Ordinary");
        assert post.addPost() : "Test failed: Valid post should return true";
    }
}

