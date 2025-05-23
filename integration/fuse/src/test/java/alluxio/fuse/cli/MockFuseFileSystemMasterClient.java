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

package alluxio.fuse.cli;

import alluxio.AlluxioURI;
import alluxio.client.file.FileSystemMasterClient;
import alluxio.client.file.ListStatusPartialResult;
import alluxio.client.file.URIStatus;
import alluxio.exception.status.AlluxioStatusException;
import alluxio.exception.status.UnavailableException;
import alluxio.grpc.CancelSyncMetadataPResponse;
import alluxio.grpc.CheckAccessPOptions;
import alluxio.grpc.CheckConsistencyPOptions;
import alluxio.grpc.CompleteFilePOptions;
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
import alluxio.grpc.RenamePOptions;
import alluxio.grpc.ScheduleAsyncPersistencePOptions;
import alluxio.grpc.SetAclAction;
import alluxio.grpc.SetAclPOptions;
import alluxio.grpc.SetAttributePOptions;
import alluxio.grpc.SyncMetadataAsyncPResponse;
import alluxio.grpc.SyncMetadataPOptions;
import alluxio.grpc.SyncMetadataPResponse;
import alluxio.grpc.UpdateUfsModePOptions;
import alluxio.job.JobDescription;
import alluxio.job.JobRequest;
import alluxio.security.authorization.AclEntry;
import alluxio.wire.MountPointInfo;
import alluxio.wire.SyncPointInfo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * A mock filesystem master client for tests.
 */
class MockFuseFileSystemMasterClient implements FileSystemMasterClient {
  @Override
  public List<AlluxioURI> checkConsistency(AlluxioURI path, CheckConsistencyPOptions options)
      throws AlluxioStatusException {
    return null;
  }

  @Override
  public void checkAccess(AlluxioURI path, CheckAccessPOptions options)
      throws AlluxioStatusException {
  }

  @Override
  public void createDirectory(AlluxioURI path, CreateDirectoryPOptions options)
      throws AlluxioStatusException {
  }

  @Override
  public URIStatus createFile(AlluxioURI path, CreateFilePOptions options)
      throws AlluxioStatusException {
    return null;
  }

  @Override
  public void completeFile(AlluxioURI path, CompleteFilePOptions options)
      throws AlluxioStatusException {
  }

  @Override
  public void delete(AlluxioURI path, DeletePOptions options) throws AlluxioStatusException {
  }

  @Override
  public boolean exists(AlluxioURI path, ExistsPOptions options)
      throws AlluxioStatusException {
    return false;
  }

  @Override
  public void free(AlluxioURI path, FreePOptions options) throws AlluxioStatusException {
  }

  @Override
  public String getFilePath(long fileId) throws AlluxioStatusException {
    return null;
  }

  @Override
  public URIStatus getStatus(AlluxioURI path, GetStatusPOptions options)
      throws AlluxioStatusException {
    return null;
  }

  @Override
  public long getNewBlockIdForFile(AlluxioURI path) throws AlluxioStatusException {
    return 0;
  }

  @Override
  public List<SyncPointInfo> getSyncPathList() throws AlluxioStatusException {
    return null;
  }

  @Override
  public void iterateStatus(AlluxioURI path, ListStatusPOptions options,
      Consumer<? super URIStatus> action) throws AlluxioStatusException {
  }

  @Override
  public List<URIStatus> listStatus(AlluxioURI path, ListStatusPOptions options)
      throws AlluxioStatusException {
    return null;
  }

  @Override
  public ListStatusPartialResult listStatusPartial(
      AlluxioURI path, ListStatusPartialPOptions options) {
    return null;
  }

  @Override
  public void mount(AlluxioURI alluxioPath, AlluxioURI ufsPath, MountPOptions options)
      throws AlluxioStatusException {
  }

  @Override
  public void updateMount(AlluxioURI alluxioPath, MountPOptions options)
      throws AlluxioStatusException {
  }

  @Override
  public Map<String, MountPointInfo> getMountTable(boolean checkUfs)
      throws AlluxioStatusException {
    return null;
  }

  @Override
  public void rename(AlluxioURI src, AlluxioURI dst) throws AlluxioStatusException {
  }

  @Override
  public void rename(AlluxioURI src, AlluxioURI dst, RenamePOptions options)
      throws AlluxioStatusException {
  }

  @Override
  public AlluxioURI reverseResolve(AlluxioURI ufsUri) throws AlluxioStatusException {
    return null;
  }

  @Override
  public void setAcl(AlluxioURI path, SetAclAction action, List<AclEntry> entries,
      SetAclPOptions options) throws AlluxioStatusException {
  }

  @Override
  public void setAttribute(AlluxioURI path, SetAttributePOptions options)
      throws AlluxioStatusException {
  }

  @Override
  public void startSync(AlluxioURI path) throws AlluxioStatusException {
  }

  @Override
  public void stopSync(AlluxioURI path) throws AlluxioStatusException {
  }

  @Override
  public void scheduleAsyncPersist(AlluxioURI path, ScheduleAsyncPersistencePOptions options)
      throws AlluxioStatusException {
  }

  @Override
  public void unmount(AlluxioURI alluxioPath) throws AlluxioStatusException {
  }

  @Override
  public void updateUfsMode(AlluxioURI ufsUri, UpdateUfsModePOptions options)
      throws AlluxioStatusException {
  }

  @Override
  public List<String> getStateLockHolders() throws AlluxioStatusException {
    return Collections.EMPTY_LIST;
  }

  @Override
  public void needsSync(AlluxioURI path) throws AlluxioStatusException {
  }

  @Override
  public Optional<String> submitJob(JobRequest job) {
    return Optional.empty();
  }

  @Override
  public boolean stopJob(JobDescription jobDescription) {
    return false;
  }

  @Override
  public String getJobProgress(JobDescription jobDescription,
      JobProgressReportFormat format, boolean verbose) {
    return null;
  }

  @Override
  public SyncMetadataPResponse syncMetadata(AlluxioURI path, SyncMetadataPOptions options)
      throws AlluxioStatusException {
    return null;
  }

  @Override
  public SyncMetadataAsyncPResponse syncMetadataAsync(AlluxioURI path, SyncMetadataPOptions options)
      throws AlluxioStatusException {
    return null;
  }

  @Override
  public GetSyncProgressPResponse getSyncProgress(long taskGroupId) throws AlluxioStatusException {
    return null;
  }

  @Override
  public CancelSyncMetadataPResponse cancelSyncMetadata(long taskGroupId)
      throws AlluxioStatusException {
    return null;
  }

  @Override
  public void connect() throws IOException {
  }

  @Override
  public void disconnect() {
  }

  @Override
  public SocketAddress getRemoteSockAddress() throws UnavailableException {
    return null;
  }

  @Override
  public String getRemoteHostName() throws UnavailableException {
    return null;
  }

  @Override
  public InetSocketAddress getConfAddress() throws UnavailableException {
    return null;
  }

  @Override
  public boolean isConnected() {
    return false;
  }

  @Override
  public boolean isClosed() {
    return false;
  }

  @Override
  public void close() throws IOException {
  }
}
