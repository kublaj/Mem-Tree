/*
 * Copyright (c) 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.drive.dredit.model;

import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.ParentReference;
import com.google.api.services.drive.model.File.Labels;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Reader;
import java.util.List;

/**
 * An object representing a File and its content, for use while interacting
 * with a DrEdit JavaScript client. Can be serialized and deserialized using
 * Gson.
 *
 * @author vicfryzel@google.com (Vic Fryzel)
 * @author nivco@google.com (Nicolas Garnier)
 */
public class ClientFile {
  /**
   * ID of file.
   */
  public String resource_id;

  /**
   * Title of file.
   */
  public String title;

  /**
   * Description of file.
   */
  public String description;

  /**
   * MIME type of file.
   */
  public String mimeType;

  /**
   * Content body of file.
   */
  public String content;

  /**
   * Is the file editable.
   */
  public boolean editable;

  /**
   * Labels.
   */
  public Labels labels;

  /**
   * parents.
   */
  public List<ParentReference> parents;

  /**
   * Empty constructor required by Gson.
   */
  public ClientFile() {}

  /**
   * Creates a new ClientFile based on the given File and content.
   */
  public ClientFile(File file, String content) {
    this.resource_id = file.getId();
    this.title = file.getTitle();
    this.description = file.getDescription();
    this.mimeType = file.getMimeType();
    this.content = content;
    this.labels = file.getLabels();
    this.editable = file.getEditable();
    this.parents = file.getParents();
  }

  /**
   * Construct a new ClientFile from its JSON representation.
   *
   * @param in Reader of JSON string to parse.
   */
  public ClientFile(Reader in) {
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();
    ClientFile other = gson.fromJson(in, ClientFile.class);
    this.resource_id = other.resource_id;
    this.title = other.title;
    this.description = other.description;
    this.mimeType = other.mimeType;
    this.content = other.content;
    this.labels = other.labels;
    this.editable = other.editable;
    this.parents = other.parents;
  }

  /**
   * @return Representation of this ClientFile as a Drive file.
   */
  public File toFile() {
    File file = new File();
    file.setId(resource_id);
    file.setTitle(title);
    file.setDescription(description);
    file.setMimeType(mimeType);
    file.setLabels(labels);
    file.setEditable(editable);
    file.setParents(parents);
    return file;
  }
}
