package com.dotmarketing.tag.business;

import com.dotmarketing.exception.DotDataException;
import com.dotmarketing.tag.model.Tag;
import com.dotmarketing.tag.model.TagInode;

import java.util.List;

public interface TagFactory {

    /**
	 * Get a list of all the tags created
	 * @return list of all tags created
	 * @throws DotDataException
	 */
    public java.util.List<Tag> getAllTags () throws DotDataException;

    /**
     * Gets all the tags matched by name
     * @param name tag name
     * @return list of tags
     * @throws DotDataException
     */
    public java.util.List<Tag> getTagsByName ( String name ) throws DotDataException;

    /**
     * Gets all the tags matched by hostId 
     * @param hostId Host Id
     * @return list of tags
     * @throws DotDataException
     */
    public java.util.List<Tag> getTagsByHost ( String hostId ) throws DotDataException;

    /**
     * Gets all the tags containing the tag name specified and the host Identifier is the specified hostId or the system host id
     * @param name  Tag name
     * @param hostId Host id
     * @return list of tags
     * @throws DotDataException
     */
    public List<Tag> getTagsLikeNameAndHostIncludingSystemHost ( String name, String hostId ) throws DotDataException;

    /**
     * Get all the tags matched by name and hostId
     * @param name  Tag name
     * @param hostId Host id
     * @return list of tags
     * @throws DotDataException
     */
    public Tag getTagByNameAndHost ( String name, String hostId ) throws DotDataException;

    /**
     * Gets a Tag by a tagId
     * @param tagId Tag identifer
     * @return a tag
     * @throws DotDataException
     */
    public Tag getTagByTagId ( String tagId ) throws DotDataException;

    /**
     * Gets all the tags associated to a user by the userproxy inode
     * @param userInode UserProxy Inode
     * @return a list of all the tags associated to a user
     * @throws DotDataException
     */
    public java.util.List<Tag> getTagsForUserByUserInode ( String userInode ) throws DotDataException;

    /**
     * Gets a subset of all tags filtered by tag name and/or host name
     * @param tagName Tag name
     * @param hostFilter Host identifier
     * @param globalTagsFilter Is a global tag filter
     * @param sort Tag field to order the results
     * @param start first record to get
     * @param count max amount of records to get
     * @return  a list of tags filtered by tag name or host name
     */
    public java.util.List<Tag> getFilteredTags ( String tagName, String hostFilter, boolean globalTagsFilter, String sort, int start, int count );

    /**
     * Update the specified tagInode related to a tag
     * @param tagInode Tag inode
     * @param tagId Tag id
     * @throws DotDataException
     */
    public void updateTagInode ( TagInode tagInode, String tagId ) throws DotDataException;

    /**
     * Creates a new tag
     * @param tagName name of the new tag
     * @param userId  owner of the new tag
     * @param hostId host identifier of the new tag
     * @return new tag created
     * @throws DotDataException
     */
    public Tag createTag ( Tag tag ) throws DotDataException;

    /**
     * Create a new TagInode
     * @param tagInode TagInode to create
     * @return new TagInode created
     * @throws DotDataException
     */
    public TagInode createTagInode ( TagInode tagInode ) throws DotDataException;

    /**
     * Update a tag object by tagId
     * @param tag Tag object to update
     * @throws DotDataException
     */
    public void updateTag ( Tag tag ) throws DotDataException;

    /**
     * Deletes a tag
     * @param tag tag to be deleted
     * @throws DotDataException
     */
    public void deleteTag ( Tag tag ) throws DotDataException;

    /**
     * Gets all TagInodes associated to an object
     * @param inode inode of the object tagged
     * @return list of all the TagInode where the tags are associated to the object
     * @throws DotDataException
     */
    public List<TagInode> getTagInodesByInode ( String inode ) throws DotDataException;

    /**
     * Gets all TagInodes associated to a tag
     * @param tagId tagId of the object tagged
     * @return list of all the TagInode where the tags are associated to the object
     * @throws DotDataException
     */
    public List<TagInode> getTagInodesByTagId ( String tagId ) throws DotDataException;

    /**
     * Gets a tagInode by name and inode
     * @param tagId id of the tag
     * @param inode inode of the object tagged
     * @param fieldVarName varname of the tag field
     * @return the tagInode
     * @throws DotDataException
     */
    public TagInode getTagInode ( String tagId, String inode, String fieldVarName ) throws DotDataException;

    /**
     * Deletes TagInodes references by inode
     * @param inode inode reference to delete
     * @throws DotDataException
     */
    public void deleteTagInodesByInode(String inode) throws DotDataException;

    /**
     * Deletes a TagInode
     * @param tagInode TagInode to delete
     * @throws DotDataException
     */
    public void deleteTagInode ( TagInode tagInode ) throws DotDataException;

    /**
     * Gets all the tags associated to an object
     * @param inode Inode of the tagged object
     * @return list of all the tags associated to an object
     * @throws DotDataException
     */
    public List<Tag> getTagsByInode ( String inode ) throws DotDataException;

}