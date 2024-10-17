package ssg.firestore_session_example;

/**
 * A class describes the how the session document will be created in 
 * Google Cloud Datastore (Firestore).
 */
public class SessionDoc {
	
	public final static String collection = "sessions";
	
	public final static String field_session_id = "session_id";
	public final static String field_created_at = "created_at";
	public final static String field_updated_at = "updated_at";
	public final static String field_accessed_at = "accessed_at";
}
