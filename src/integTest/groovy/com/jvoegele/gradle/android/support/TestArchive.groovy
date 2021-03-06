/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jvoegele.gradle.android.support

import java.util.jar.JarFile

import static org.junit.Assert.assertNotNull

class TestArchive {
  def project
  def archive

  def assertContains(path) {
    def jar = new JarFile(archive)
    assertNotNull "The archive ${archive} must contain ${path}", jar.getEntry(path)
  }

  def assertAligned() {
    new ZipAlignVerifier(project: project).verifyAligned archive
  }

  def assertSigned(distinguishedName) {
    new SignVerifier(archive: archive).verify(distinguishedName)
  }
}
