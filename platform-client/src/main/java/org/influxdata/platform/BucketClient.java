/*
 * The MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.influxdata.platform;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.influxdata.platform.domain.Bucket;
import org.influxdata.platform.domain.Organization;
import org.influxdata.platform.domain.ResourceMember;
import org.influxdata.platform.domain.RetentionRule;
import org.influxdata.platform.domain.User;

/**
 * The client of the InfluxData Platform that implement Bucket HTTP API endpoint.
 *
 * @author Jakub Bednar (bednar@github) (13/09/2018 10:31)
 */
public interface BucketClient {

    /**
     * Creates a new bucket and sets {@link Bucket#getId()} with the new identifier.
     *
     * @param bucket bucket to create
     * @return Bucket created
     */
    @Nonnull
    Bucket createBucket(@Nonnull Bucket bucket);

    /**
     * Creates a new bucket and sets {@link Bucket#getId()}  with the new identifier.
     *
     * @param name            name of the bucket
     * @param organization    owner of bucket
     * @return Bucket created
     */
    @Nonnull
    Bucket createBucket(@Nonnull final String name,
                        @Nonnull final Organization organization);

    /**
     * Creates a new bucket and sets {@link Bucket#getId()} with the new identifier.
     *
     * @param name            name of the bucket
     * @param retentionRule bucket retention period
     * @param organization    owner of bucket
     * @return Bucket created
     */
    @Nonnull
    Bucket createBucket(@Nonnull final String name,
                        @Nullable final RetentionRule retentionRule,
                        @Nonnull final Organization organization);

    /**
     * Creates a new bucket and sets {@link Bucket#getId()} with the new identifier.
     *
     * @param name             name of the bucket
     * @param organizationName owner of bucket
     * @return Bucket created
     */
    @Nonnull
    Bucket createBucket(@Nonnull final String name,
                        @Nonnull final String organizationName);

    /**
     * Creates a new bucket and sets {@link Bucket#getId()} with the new identifier.
     *
     * @param name             name of the bucket
     * @param retentionRule  bucket retention period
     * @param organizationName owner of bucket
     * @return Bucket created
     */
    @Nonnull
    Bucket createBucket(@Nonnull final String name,
                        @Nullable final RetentionRule retentionRule,
                        @Nonnull final String organizationName);

    /**
     * Update a bucket name and retention.
     *
     * @param bucket bucket update to apply
     * @return bucket updated
     */
    @Nonnull
    Bucket updateBucket(@Nonnull final Bucket bucket);

    /**
     * Delete a bucket.
     *
     * @param bucket bucket to delete
     */
    void deleteBucket(@Nonnull final Bucket bucket);

    /**
     * Delete a bucket.
     *
     * @param bucketID ID of bucket to delete
     */
    void deleteBucket(@Nonnull final String bucketID);

    /**
     * Retrieve a bucket.
     *
     * @param bucketID ID of bucket to get
     * @return bucket details
     */
    @Nullable
    Bucket findBucketByID(@Nonnull final String bucketID);

    /**
     * List all buckets.
     *
     * @return List all buckets
     */
    @Nonnull
    List<Bucket> findBuckets();

    /**
     * List all buckets for specified {@code organization}.
     *
     * @param organization filter buckets to a specific organization
     * @return A list of buckets
     */
    @Nonnull
    List<Bucket> findBucketsByOrganization(@Nonnull final Organization organization);

    /**
     * List all buckets for specified {@code organizationName}.
     *
     * @param organizationName filter buckets to a specific organization name
     * @return A list of buckets
     */
    @Nonnull
    List<Bucket> findBucketsByOrganizationName(@Nullable final String organizationName);

    /**
     * List all users with member privileges for a bucket.
     *
     * @param bucketID ID of bucket to get members
     * @return return the list all users with member privileges for a bucket
     */
    @Nonnull
    List<ResourceMember> getMembers(@Nonnull final String bucketID);

    /**
     * List all users with member privileges for a bucket.
     *
     * @param bucket the bucket with members
     * @return return the list all users with member privileges for a bucket
     */
    @Nonnull
    List<ResourceMember> getMembers(@Nonnull final Bucket bucket);

    /**
     * Add the bucket member.
     *
     * @param member the member of an bucket
     * @param bucket the bucket for the member
     * @return created mapping
     */
    @Nonnull
    ResourceMember addMember(@Nonnull final User member, @Nonnull final Bucket bucket);

    /**
     * Add the bucket member.
     *
     * @param memberID the ID of a member
     * @param bucketID the ID of a bucket
     * @return created mapping
     */
    @Nonnull
    ResourceMember addMember(@Nonnull final String memberID, @Nonnull final String bucketID);

    /**
     * Removes a member from a bucket.
     *
     * @param member the member of a bucket
     * @param bucket the bucket
     */
    void deleteMember(@Nonnull final User member, @Nonnull final Bucket bucket);

    /**
     * Removes a member from a bucket.
     *
     * @param bucketID the ID of a bucket
     * @param memberID the ID of a member
     */
    void deleteMember(@Nonnull final String memberID, @Nonnull final String bucketID);

    /**
     * List all owners of a bucket.
     *
     * @param bucketID ID of bucket to get owners
     * @return return List all owners of a bucket
     */
    @Nonnull
    List<ResourceMember> getOwners(@Nonnull final String bucketID);

    /**
     * List all owners of a bucket.
     *
     * @param bucket the bucket with owners
     * @return return List all owners of a bucket.
     */
    @Nonnull
    List<ResourceMember> getOwners(@Nonnull final Bucket bucket);

    /**
     * Add the bucket owner.
     *
     * @param owner  the owner of a bucket
     * @param bucket the bucket
     * @return created mapping
     */
    @Nonnull
    ResourceMember addOwner(@Nonnull final User owner, @Nonnull final Bucket bucket);

    /**
     * Add the bucket owner.
     *
     * @param bucketID the ID of a bucket
     * @param ownerID  the ID of a owner
     * @return created mapping
     */
    @Nonnull
    ResourceMember addOwner(@Nonnull final String ownerID, @Nonnull final String bucketID);

    /**
     * Removes a owner from a bucket.
     *
     * @param owner  the owner of a bucket
     * @param bucket the bucket
     */
    void deleteOwner(@Nonnull final User owner, @Nonnull final Bucket bucket);

    /**
     * Removes a owner from a bucket.
     *
     * @param bucketID the ID of a bucket
     * @param ownerID  the ID of a owner
     */
    void deleteOwner(@Nonnull final String ownerID, @Nonnull final String bucketID);
}