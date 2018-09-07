package com.redhat.proksch.demo.warehouseaccess;
  
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown=true)
@Data
public class Weather {

        public Weather() {}
        private String zip;
        private String weather;
        private boolean found;

	public String getZip() { return this.zip; }
	public void setZip(String zip) { this.zip = zip; }

	public boolean getFound() { return this.found; }
	public void setFound(boolean found) { this.found = found; }
}
