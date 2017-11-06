package by.wink.core.rest;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import by.wink.core.rest.utilities.MultipartRequest;

/**
 * Created by zoid23 on 06/11/17.
 */

public class CoreMultipartRequest extends MultipartRequest {

    private CoreResponseListener<String> listener;
    private Map<String, String> params;
    private Map<String, DataPart> multipart;

    public CoreMultipartRequest(String url, Map<String, String> headers, Map<String, String> params, Map<String, DataPart> multipart, CoreResponseListener<String> listener) {
        super(url, headers, listener, listener.getErrorListener());
        this.listener = listener;
        this.params = params;
        this.multipart = multipart;
    }

    @Override
    protected Response<NetworkResponse> parseNetworkResponse(NetworkResponse response) {
        listener.setResHeaders(response.headers);
        return super.parseNetworkResponse(response);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }

    @Override
    protected Map<String, MultipartRequest.DataPart> getByteData() throws AuthFailureError {
        Map<String, MultipartRequest.DataPart> casted = new HashMap<>();
        for(String key : multipart.keySet())
            casted.put(key, multipart.get(key));
        return casted;
    }

    public static class DataPart extends MultipartRequest.DataPart {
        /**
         * Constructor with data.
         *
         * @param name label of data
         * @param file file data
         */
        public DataPart(String name, File file) throws IOException {
            super(name, read(file));
        }

        /**
         * Constructor with mime data type.
         *
         * @param name     label of data
         * @param file file data
         * @param mimeType mime data like "image/jpeg"
         */
        public DataPart(String name, File file, String mimeType) throws IOException {
            super(name, read(file), mimeType);
        }


        private static byte[] read(File file) throws IOException {
            ByteArrayOutputStream ous = null;
            InputStream ios = null;
            try {
                byte[] buffer = new byte[4096];
                ous = new ByteArrayOutputStream();
                ios = new FileInputStream(file);
                int read = 0;
                while ((read = ios.read(buffer)) != -1) {
                    ous.write(buffer, 0, read);
                }
            }finally {
                try {
                    if (ous != null)
                        ous.close();
                } catch (IOException e) {
                }

                try {
                    if (ios != null)
                        ios.close();
                } catch (IOException e) {
                }
            }
            return ous.toByteArray();
        }
    }
}