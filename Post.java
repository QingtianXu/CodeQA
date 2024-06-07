import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Post {
    private int postID;
    private String postTitle;
    private String postBody;
    private String[] postTags;
    private String postType;
    private String postEmergency;
    private ArrayList<String> postComments = new ArrayList<>();

    // Regular expression pattern to validate the post title
    private static final Pattern TITLE_PATTERN = Pattern.compile("^[^0-9\\W]{5}.*");

    // Constructor
    public Post(int postID, String postTitle, String postBody, String[] postTags, String postType, String postEmergency) {
        this.postID = postID;
        this.postTitle = postTitle;
        this.postBody = postBody;
        this.postTags = postTags;
        this.postType = postType;
        this.postEmergency = postEmergency;
    }

    // Method to add a post
    public boolean addPost() {
        // Validate title length and pattern
        if (postTitle.length() < 10 || postTitle.length() > 250 || !TITLE_PATTERN.matcher(postTitle).matches()) {
            return false;
        }
        // Validate body length
        if (postBody.length() < 250) {
            return false;
        }
        // Validate tags
        if (postTags.length < 2 || postTags.length > 5) {
            return false;
        }
        for (String tag : postTags) {
            if (tag.length() < 2 || tag.length() > 10 || !tag.equals(tag.toLowerCase())) {
                return false;
            }
        }
        // Validate post type and tag count
        if (postType.equals("Easy") && postTags.length > 3) {
            return false;
        }
        // Validate post type and body length
        if ((postType.equals("Very Difficult") || postType.equals("Difficult")) && postBody.length() < 300) {
            return false;
        }
        // Validate post type and emergency status
        if (postType.equals("Easy") && (postEmergency.equals("Immediately Needed") || postEmergency.equals("Highly Needed"))) {
            return false;
        }
        if ((postType.equals("Very Difficult") || postType.equals("Difficult")) && postEmergency.equals("Ordinary")) {
            return false;
        }

        // Write post to file
        try (FileWriter writer = new FileWriter("post.txt", true)) {
            writer.write(postID + "\n" + postTitle + "\n" + postBody + "\n" + String.join(", ", postTags) + "\n" + postType + "\n" + postEmergency + "\n\n");
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    // Method to add a comment to a post
    public boolean addComment(String commentText) {
        // Validate comment length and starting character
        if (commentText.split(" ").length < 4 || commentText.split(" ").length > 10 || !Character.isUpperCase(commentText.charAt(0))) {
            return false;
        }

        // Validate the number of comments
        if (postComments.size() >= 5 || (postComments.size() >= 3 && isEasyOrOrdinary())) {
            return false;
        }

        postComments.add(commentText);

        // Write comment to file
        try (FileWriter writer = new FileWriter("comment.txt", true)) {
            writer.write(postID + "\n" + commentText + "\n\n");
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    // Helper method to check if the post is of type Easy or has Ordinary emergency status
    private boolean isEasyOrOrdinary() {
        return postType.equals("Easy") || postEmergency.equals("Ordinary");
    }
}
