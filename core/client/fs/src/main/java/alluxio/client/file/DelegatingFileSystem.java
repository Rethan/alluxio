/*
 * The Alluxio Open Foundation licenses this work under the Apache License, version 2.0
 * (the "License"). You may not use this work except in compliance with the License, which is
 * available at www.apache.org/licenses/LICENSE-2.0
 *
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied, as more fully set forth in the License.
 *
 * See the NOTICE file distributed with this work for information regarding copyright ownership.
 */

package alluxio.client.file;

import alluxio.AlluxioURI;
import alluxio.conf.AlluxioConfiguration;
import alluxio.exception.AlluxioException;
import alluxio.exception.DirectoryNotEmptyException;
import alluxio.exception.FileAlreadyExistsException;
import alluxio.exception.FileDoesNotExistException;
import alluxio.exception.FileIncompleteException;
import alluxio.exception.InvalidPathException;
import alluxio.exception.OpenDirectoryException;
import alluxio.grpc.CancelSyncMetadataPResponse;
import alluxio.grpc.CheckAccessPOptions;
import alluxio.grpc.CreateDirectoryPOptions;
import alluxio.grpc.CreateFilePOptions;
import alluxio.grpc.DeletePOptions;
import alluxio.grpc.ExistsPOptions;
import alluxio.grpc.FreePOptions;
import alluxio.grpc.GetStatusPOptions;
import alluxio.grpc.GetSyncProgressPResponse;
import alluxio.grpc.JobProgressReportFormat;
import alluxio.grpc.ListStatusPOptions;
import alluxio.grpc.ListStatusPartialPOptions;
import alluxio.grpc.MountPOptions;
import alluxio.grpc.OpenFilePOptions;
import alluxio.grpc.RenamePOptions;
import alluxio.grpc.ScheduleAsyncPersistencePOptions;
import alluxio.grpc.SetAclAction;
import alluxio.grpc.SetAclPOptions;
import alluxio.grpc.SetAttributePOptions;
import alluxio.grpc.SyncMetadataAsyncPResponse;
import alluxio.grpc.SyncMetadataPOptions;
import alluxio.grpc.SyncMetadataPResponse;
import alluxio.grpc.UnmountPOptions;
import alluxio.job.JobDescription;
import alluxio.job.JobRequest;
import alluxio.security.authorization.AclEntry;
import alluxio.wire.BlockLocationInfo;
import alluxio.wire.MountPointInfo;
import alluxio.wire.SyncPointInfo;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * A wrapper of a FileSystem instance.
 */
public class DelegatingFileSystem implements FileSystem {
  protected final FileSystem mDelegatedFileSystem;

  /**
   * Wraps a file system instance to forward messages.
   *
   * @param fs the underlying file system
   */
  public DelegatingFileSystem(FileSystem fs) {
    mDelegatedFileSystem = fs;
  }

  @Override
  public boolean isClosed() {
    return mDelegatedFileSystem.isClosed();
  }

  @Override
  public void checkAccess(AlluxioURI path, CheckAccessPOptions options)
      throws InvalidPathException, IOException, AlluxioException {
    mDelegatedFileSystem.checkAccess(path, options);
  }

  @Override
  public void createDirectory(AlluxioURI path, CreateDirectoryPOptions options)
      throws FileAlreadyExistsException, InvalidPathException, IOException, AlluxioException {
    mDelegatedFileSystem.createDirectory(path, options);
  }

  @Override
  public FileOutStream createFile(AlluxioURI path, CreateFilePOptions options)
      throws FileAlreadyExistsException, InvalidPathException, IOException, AlluxioException {
    return mDelegatedFileSystem.createFile(path, options);
  }

  @Override
  public void delete(AlluxioURI path, DeletePOptions options)
      throws DirectoryNotEmptyException, FileDoesNotExistException, IOException, AlluxioException {
    mDelegatedFileSystem.delete(path, options);
  }

  @Override
  public boolean exists(AlluxioURI path, ExistsPOptions options)
      throws InvalidPathException, IOException, AlluxioException {
    return mDelegatedFileSystem.exists(path, options);
  }

  @Override
  public void free(AlluxioURI path, FreePOptions options)
      throws FileDoesNotExistException, IOException, AlluxioException {
    mDelegatedFileSystem.free(path, options);
  }

  @Override
  public List<BlockLocationInfo> getBlockLocations(AlluxioURI path)
      throws FileDoesNotExistException, IOException, AlluxioException {
    return mDelegatedFileSystem.getBlockLocations(path);
  }

  @Override
  public List<BlockLocationInfo> getBlockLocations(URIStatus status)
      throws FileDoesNotExistException, IOException, AlluxioException {
    return mDelegatedFileSystem.getBlockLocations(status);
  }

  @Override
  public AlluxioConfiguration getConf() {
    return mDelegatedFileSystem.getConf();
  }

  @Override
  public URIStatus getStatus(AlluxioURI path, GetStatusPOptions options)
      throws FileDoesNotExistException, IOException, AlluxioException {
    return mDelegatedFileSystem.getStatus(path, options);
  }

  @Override
  public List<URIStatus> listStatus(AlluxioURI path, ListStatusPOptions options)
      throws FileDoesNotExistException, IOException, AlluxioException {
    return mDelegatedFileSystem.listStatus(path, options);
  }

  @Override
  public ListStatusPartialResult listStatusPartial(
      AlluxioURI path, ListStatusPartialPOptions options)
      throws AlluxioException, IOException {
    return mDelegatedFileSystem.listStatusPartial(path, options);
  }

  @Override
  public void iterateStatus(AlluxioURI path, ListStatusPOptions options,
      Consumer<? super URIStatus> action)
      throws FileDoesNotExistException, IOException, AlluxioException {
    mDelegatedFileSystem.iterateStatus(path, options, action);
  }

  @Override
  public void loadMetadata(AlluxioURI path, ListStatusPOptions options)
      throws FileDoesNotExistException, IOException, AlluxioException {
    mDelegatedFileSystem.loadMetadata(path, options);
  }

  @Override
  public void mount(AlluxioURI alluxioPath, AlluxioURI ufsPath, MountPOptions options)
      throws IOException, AlluxioException {
    mDelegatedFileSystem.mount(alluxioPath, ufsPath, options);
  }

  @Override
  public void updateMount(AlluxioURI alluxioPath, MountPOptions options)
      throws IOException, AlluxioException {
    mDelegatedFileSystem.updateMount(alluxioPath, options);
  }

  @Override
  public Map<String, MountPointInfo> getMountTable(boolean checkUfs)
      throws IOException, AlluxioException {
    return mDelegatedFileSystem.getMountTable(checkUfs);
  }

  @Override
  public List<SyncPointInfo> getSyncPathList() throws IOException, AlluxioException {
    return mDelegatedFileSystem.getSyncPathList();
  }

  @Override
  public FileInStream openFile(AlluxioURI path, OpenFilePOptions options)
      throws FileDoesNotExistException, OpenDirectoryException, FileIncompleteException,
      IOException, AlluxioException {
    return mDelegatedFileSystem.openFile(path, options);
  }

  @Override
  public FileInStream openFile(URIStatus status, OpenFilePOptions options)
      throws FileDoesNotExistException, OpenDirectoryException, FileIncompleteException,
      IOException, AlluxioException {
    return mDelegatedFileSystem.openFile(status, options);
  }

  @Override
  public void persist(AlluxioURI path, ScheduleAsyncPersistencePOptions options)
      throws FileDoesNotExistException, IOException, AlluxioException {
    mDelegatedFileSystem.persist(path, options);
  }

  @Override
  public void rename(AlluxioURI src, AlluxioURI dst, RenamePOptions options)
      throws FileDoesNotExistException, IOException, AlluxioException {
    mDelegatedFileSystem.rename(src, dst, options);
  }

  @Override
  public AlluxioURI reverseResolve(AlluxioURI ufsUri) throws IOException, AlluxioException {
    return mDelegatedFileSystem.reverseResolve(ufsUri);
  }

  @Override
  public void setAcl(AlluxioURI path, SetAclAction action, List<AclEntry> entries,
      SetAclPOptions options) throws FileDoesNotExistException, IOException, AlluxioException {
    mDelegatedFileSystem.setAcl(path, action, entries, options);
  }

  @Override
  public void startSync(AlluxioURI path)
      throws FileDoesNotExistException, IOException, AlluxioException {
    mDelegatedFileSystem.startSync(path);
  }

  @Override
  public void stopSync(AlluxioURI path)
      throws FileDoesNotExistException, IOException, AlluxioException {
    mDelegatedFileSystem.stopSync(path);
  }

  @Override
  public void setAttribute(AlluxioURI path, SetAttributePOptions options)
      throws FileDoesNotExistException, IOException, AlluxioException {
    mDelegatedFileSystem.setAttribute(path, options);
  }

  @Override
  public void unmount(AlluxioURI path, UnmountPOptions options)
      throws IOException, AlluxioException {
    mDelegatedFileSystem.unmount(path, options);
  }

  @Override
  public void needsSync(AlluxioURI path) throws IOException, AlluxioException {
    mDelegatedFileSystem.needsSync(path);
  }

  @Override
  public Optional<String> submitJob(JobRequest jobRequest) {
    return mDelegatedFileSystem.submitJob(jobRequest);
  }

  @Override
  public boolean stopJob(JobDescription jobDescription) {
    return mDelegatedFileSystem.stopJob(jobDescription);
  }

  @Override
  public String getJobProgress(JobDescription jobDescription,
      JobProgressReportFormat format, boolean verbose) {
    return mDelegatedFileSystem.getJobProgress(jobDescription, format, verbose);
  }

  @Override
  public SyncMetadataPResponse syncMetadata(AlluxioURI path, SyncMetadataPOptions options)
      throws FileDoesNotExistException, IOException, AlluxioException {
    return mDelegatedFileSystem.syncMetadata(path, options);
  }

  @Override
  public SyncMetadataAsyncPResponse syncMetadataAsync(AlluxioURI path, SyncMetadataPOptions options)
      throws FileDoesNotExistException, IOException, AlluxioException {
    return mDelegatedFileSystem.syncMetadataAsync(path, options);
  }

  @Override
  public GetSyncProgressPResponse getSyncProgress(long taskGroupId)
      throws FileDoesNotExistException, IOException, AlluxioException {
    return mDelegatedFileSystem.getSyncProgress(taskGroupId);
  }

  @Override
  public CancelSyncMetadataPResponse cancelSyncMetadata(long taskGroupId)
      throws IOException, AlluxioException {
    return mDelegatedFileSystem.cancelSyncMetadata(taskGroupId);
  }

  @Override
  public void close() throws IOException {
    mDelegatedFileSystem.close();
  }

  /**
   * @return the underlying fileSystem
   */
  public FileSystem getUnderlyingFileSystem() {
    return mDelegatedFileSystem;
  }
}
