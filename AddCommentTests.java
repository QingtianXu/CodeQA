public class AddCommentTests {

    // Method to repeat a string multiple times
    public static String repeat(String s, int count) {
        return new String(new char[count]).replace("\0", s);
    }

    // Main method to run the tests
    public static void main(String[] args) {
        testCommentTooShort();
        testCommentTooLong();
        testCommentStartsWithLowercase();
        testCommentValid();
        testTooManyComments();
        testValidComment();
        System.out.println("All tests passed.");
    }

    // Test for a comment that is too short
    public static void testCommentTooShort() {
        Post post = new Post(1, "Valid Title", "This is a valid body with more than 250 characters..." + repeat("A", 225), new String[]{"tag1", "tag2"}, "Easy", "Ordinary");
        assert !post.addComment("Too short") : "Test failed: Comment too short should return false";
    }

    // Test for a comment that is too long
    public static void testCommentTooLong() {
        Post post = new Post(2, "Valid Title", "This is a valid body with more than 250 characters..." + repeat("A", 225), new String[]{"tag1", "tag2"}, "Easy", "Ordinary");
        assert !post.addComment("This comment is way too long for the specified conditions because it exceeds ten words") : "Test failed: Comment too long should return false";
    }

    // Test for a comment that starts with a lowercase letter
    public static void testCommentStartsWithLowercase() {
        Post post = new Post(3, "Valid Title", "This is a valid body with more than 250 characters..." + repeat("A", 225), new String[]{"tag1", "tag2"}, "Easy", "Ordinary");
        assert !post.addComment("this is a comment") : "Test failed: Comment starts with lowercase should return false";
    }

    // Test for a valid comment
    public static void testCommentValid() {
        Post post = new Post(4, "Valid Title", "This is a valid body with more than 250 characters..." + repeat("A", 225), new String[]{"tag1", "tag2"}, "Easy", "Ordinary");
        assert post.addComment("This is valid") : "Test failed: Valid comment should return true";
    }

    // Test for too many comments
    public static void testTooManyComments() {
        Post post = new Post(5, "Valid Title", "This is a valid body with more than 250 characters..." + repeat("A", 225), new String[]{"tag1", "tag2"}, "Easy", "Ordinary");
        for (int i = 0; i < 5; i++) {
            post.addComment("Valid comment");
        }
        assert !post.addComment("Another valid comment") : "Test failed: Too many comments should return false";
    }

    // Test for a valid comment under normal conditions
    public static void testValidComment() {
        Post post = new Post(6, "Valid Title", "This is a valid body with more than 250 characters..." + repeat("A", 225), new String[]{"tag1", "tag2"}, "Easy", "Ordinary");
        assert post.addComment("This is okay") : "Test failed: Valid comment should return true";
    }
}
