package datamining.cluster;

import java.util.ArrayList;

public interface ICluster {
	public ArrayList<ICluster> getUnits();
	public void setUnits(ArrayList<ICluster> clusterList);
	public void doInnerClustering() throws Exception;
	public ICluster getParentCluster();
	public void setParentCluster(ICluster cluster);
}
