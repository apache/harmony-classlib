/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.harmony.pack200;

import java.io.IOException;
import java.io.OutputStream;

/**
 * SegmentHeader is the header band of a {@link Segment}
 */
public class SegmentHeader extends BandSet {

    private static final int[] magic = { 0xCA, 0xFE, 0xD0, 0x0D };
    private static final int archive_minver = 7;
    private static final int archive_majver = 150;

    private int archive_options;

    private int cp_Utf8_count;
    private int cp_Int_count;
    private int cp_Float_count;
    private int cp_Long_count;
    private int cp_Double_count;
    private int cp_String_count;
    private int cp_Class_count;
    private int cp_Signature_count;
    private int cp_Descr_count;
    private int cp_Field_count;
    private int cp_Method_count;
    private int cp_Imethod_count;

    private int attribute_definition_count;
    private final byte[] band_headers = new byte[0];

    private boolean have_all_code_flags;

    private int archive_size_hi;
    private int archive_size_lo;
    private int archive_next_count;
    private int archive_modtime;
    private int file_count;

    private boolean deflate_hint;
    private boolean have_file_modtime;
    private boolean  have_file_options;
    private boolean  have_file_size_hi;
    private boolean  have_class_flags_hi;
    private boolean  have_field_flags_hi;
    private boolean  have_method_flags_hi;
    private boolean  have_code_flags_hi;

    private int ic_count;
    private int default_class_minver;
    private int default_class_majver;
    private int class_count;

    /**
     * Encode and write the SegmentHeader bands to the OutputStream
     */
    public void pack(OutputStream out) throws IOException, Pack200Exception {
        out.write(encodeScalar(magic, Codec.BYTE1));
        out.write(encodeScalar(archive_minver, Codec.UNSIGNED5));
        out.write(encodeScalar(archive_majver, Codec.UNSIGNED5));
        calculateArchiveOptions();
        out.write(encodeScalar(archive_options, Codec.UNSIGNED5));
        writeArchiveFileCounts(out);
        writeArchiveSpecialCounts(out);
        writeCpCounts(out);
        writeClassCounts(out);
        if (band_headers.length > 0) {
            out.write(band_headers);
        }
    }

    private void calculateArchiveOptions() {
        if(attribute_definition_count > 0 || band_headers.length > 0) {
            archive_options |= 1;
        }
        if(cp_Int_count > 0 || cp_Float_count > 0 || cp_Long_count > 0 || cp_Double_count > 0) {
            archive_options |= (1 << 1);
        }
        if(have_all_code_flags) {
            archive_options |= (1 << 2);
        }
        if(file_count > 0) {
            archive_options |= (1 << 4);
        }
        if(deflate_hint) {
            archive_options |= (1 << 5);
        }
        if(have_file_modtime) {
            archive_options |= (1 << 6);
        }
        if(have_file_options) {
            archive_options |= (1 << 7);
        }
        if(have_file_size_hi) {
            archive_options |= (1 << 8);
        }
        if(have_class_flags_hi) {
            archive_options |= (1 << 9);
        }
        if(have_field_flags_hi) {
            archive_options |= (1 << 10);
        }
        if(have_method_flags_hi) {
            archive_options |= (1 << 11);
        }
        if(have_code_flags_hi) {
            archive_options |= (1 << 12);
        }
    }

    public void setCp_Utf8_count(int count) {
        cp_Utf8_count = count;
    }

    public void setCp_Int_count(int count) {
        cp_Int_count = count;
    }

    public void setCp_Float_count(int count) {
        cp_Float_count = count;
    }

    public void setCp_Long_count(int count) {
        cp_Long_count = count;
    }

    public void setCp_Double_count(int count) {
        cp_Double_count = count;
    }

    public void setCp_String_count(int count) {
        cp_String_count = count;
    }

    public void setCp_Class_count(int count) {
        cp_Class_count = count;
    }

    public void setCp_Signature_count(int count) {
        cp_Signature_count = count;
    }

    public void setCp_Descr_count(int count) {
        cp_Descr_count = count;
    }

    public void setCp_Field_count(int count) {
        cp_Field_count = count;
    }

    public void setCp_Method_count(int count) {
        cp_Method_count = count;
    }

    public void setCp_Imethod_count(int count) {
        cp_Imethod_count = count;
    }

    public void setAttributeDefinition_count(int count) {
        attribute_definition_count = count;
    }


    public void setAttribute_definition_count(int attribute_definition_count) {
        this.attribute_definition_count = attribute_definition_count;
    }


    public void setHave_all_code_flags(boolean have_all_code_flags) {
        this.have_all_code_flags = have_all_code_flags;
    }


    public void setArchive_size_hi(int archive_size_hi) {
        this.archive_size_hi = archive_size_hi;
    }


    public void setArchive_size_lo(int archive_size_lo) {
        this.archive_size_lo = archive_size_lo;
    }


    public void setArchive_next_count(int archive_next_count) {
        this.archive_next_count = archive_next_count;
    }


    public void setArchive_modtime(int archive_modtime) {
        this.archive_modtime = archive_modtime;
    }


    public void setFile_count(int file_count) {
        this.file_count = file_count;
    }


    public void setDeflate_hint(boolean deflate_hint) {
        this.deflate_hint = deflate_hint;
    }


    public void setHave_file_modtime(boolean have_file_modtime) {
        this.have_file_modtime = have_file_modtime;
    }


    public void setHave_file_options(boolean have_file_options) {
        this.have_file_options = have_file_options;
    }


    public void setHave_file_size_hi(boolean have_file_size_hi) {
        this.have_file_size_hi = have_file_size_hi;
    }


    public void setHave_class_flags_hi(boolean have_class_flags_hi) {
        this.have_class_flags_hi = have_class_flags_hi;
    }


    public void setHave_field_flags_hi(boolean have_field_flags_hi) {
        this.have_field_flags_hi = have_field_flags_hi;
    }


    public void setHave_method_flags_hi(boolean have_method_flags_hi) {
        this.have_method_flags_hi = have_method_flags_hi;
    }


    public void setHave_code_flags_hi(boolean have_code_flags_hi) {
        this.have_code_flags_hi = have_code_flags_hi;
    }


    public void setIc_count(int ic_count) {
        this.ic_count = ic_count;
    }


    public void setDefault_class_minver(int default_class_minver) {
        this.default_class_minver = default_class_minver;
    }


    public void setDefault_class_majver(int default_class_majver) {
        this.default_class_majver = default_class_majver;
    }


    public void setClass_count(int class_count) {
        this.class_count = class_count;
    }

    private void writeCpCounts(OutputStream out) throws IOException, Pack200Exception {
        out.write(encodeScalar(cp_Utf8_count, Codec.UNSIGNED5));
        if((archive_options & (1 << 1)) != 0) { // have_cp_numbers
            out.write(encodeScalar(cp_Int_count, Codec.UNSIGNED5));
            out.write(encodeScalar(cp_Float_count, Codec.UNSIGNED5));
            out.write(encodeScalar(cp_Long_count, Codec.UNSIGNED5));
            out.write(encodeScalar(cp_Double_count, Codec.UNSIGNED5));
        }
        out.write(encodeScalar(cp_String_count, Codec.UNSIGNED5));
        out.write(encodeScalar(cp_Class_count, Codec.UNSIGNED5));
        out.write(encodeScalar(cp_Signature_count, Codec.UNSIGNED5));
        out.write(encodeScalar(cp_Descr_count, Codec.UNSIGNED5));
        out.write(encodeScalar(cp_Field_count, Codec.UNSIGNED5));
        out.write(encodeScalar(cp_Method_count, Codec.UNSIGNED5));
        out.write(encodeScalar(cp_Imethod_count, Codec.UNSIGNED5));
    }

    private void writeClassCounts(OutputStream out) throws IOException, Pack200Exception {
        out.write(encodeScalar(ic_count, Codec.UNSIGNED5));
        out.write(encodeScalar(default_class_minver, Codec.UNSIGNED5));
        out.write(encodeScalar(default_class_majver, Codec.UNSIGNED5));
        out.write(encodeScalar(class_count, Codec.UNSIGNED5));
    }

    private void writeArchiveSpecialCounts(OutputStream out) throws IOException, Pack200Exception {
        if((archive_options & 1) > 0) { // have_special_formats
            out.write(encodeScalar(band_headers.length, Codec.UNSIGNED5));
            out.write(encodeScalar(attribute_definition_count, Codec.UNSIGNED5));
        }
    }

    private void writeArchiveFileCounts(OutputStream out) throws IOException, Pack200Exception {
        if((archive_options & (1 << 4)) > 0) { // have_file_headers
            out.write(encodeScalar(archive_size_hi, Codec.UNSIGNED5));
            out.write(encodeScalar(archive_size_lo, Codec.UNSIGNED5));
            out.write(encodeScalar(archive_next_count, Codec.UNSIGNED5));
            out.write(encodeScalar(archive_modtime, Codec.UNSIGNED5));
            out.write(encodeScalar(file_count, Codec.UNSIGNED5));
        }
    }

}