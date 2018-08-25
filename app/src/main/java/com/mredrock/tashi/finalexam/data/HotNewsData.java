package com.mredrock.tashi.finalexam.data;

import java.util.List;

public class HotNewsData {


    private List<AreaListBean> areaList;
    private List<DataListBean> dataList;

    public List<AreaListBean> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<AreaListBean> areaList) {
        this.areaList = areaList;
    }

    public List<DataListBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataListBean> dataList) {
        this.dataList = dataList;
    }

    public static class AreaListBean {
        /**
         * area_id : 100001
         * expInfo : {"algorighm_exp_id":"","front_exp_id":"0","s_value":"e2416e64-b0ec-4f28-a73b-c1815776a9e2_44857770534489814"}
         */

        private String area_id;
        private ExpInfoBean expInfo;

        public String getArea_id() {
            return area_id;
        }

        public void setArea_id(String area_id) {
            this.area_id = area_id;
        }

        public ExpInfoBean getExpInfo() {
            return expInfo;
        }

        public void setExpInfo(ExpInfoBean expInfo) {
            this.expInfo = expInfo;
        }

        public static class ExpInfoBean {
            /**
             * algorighm_exp_id :
             * front_exp_id : 0
             * s_value : e2416e64-b0ec-4f28-a73b-c1815776a9e2_44857770534489814
             */

            private String algorighm_exp_id;
            private String front_exp_id;
            private String s_value;

            public String getAlgorighm_exp_id() {
                return algorighm_exp_id;
            }

            public void setAlgorighm_exp_id(String algorighm_exp_id) {
                this.algorighm_exp_id = algorighm_exp_id;
            }

            public String getFront_exp_id() {
                return front_exp_id;
            }

            public void setFront_exp_id(String front_exp_id) {
                this.front_exp_id = front_exp_id;
            }

            public String getS_value() {
                return s_value;
            }

            public void setS_value(String s_value) {
                this.s_value = s_value;
            }
        }
    }

    public static class DataListBean {
        private String nodeType;
        private String nodeName;
        private String isOrder;
        private String nodeLogo;
        private String nodeDesc;
        private String moreId;
        private List<ContListBean> contList;

        public String getNodeType() {
            return nodeType;
        }

        public void setNodeType(String nodeType) {
            this.nodeType = nodeType;
        }

        public String getNodeName() {
            return nodeName;
        }

        public void setNodeName(String nodeName) {
            this.nodeName = nodeName;
        }

        public String getIsOrder() {
            return isOrder;
        }

        public void setIsOrder(String isOrder) {
            this.isOrder = isOrder;
        }

        public String getNodeLogo() {
            return nodeLogo;
        }

        public void setNodeLogo(String nodeLogo) {
            this.nodeLogo = nodeLogo;
        }

        public String getNodeDesc() {
            return nodeDesc;
        }

        public void setNodeDesc(String nodeDesc) {
            this.nodeDesc = nodeDesc;
        }

        public String getMoreId() {
            return moreId;
        }

        public void setMoreId(String moreId) {
            this.moreId = moreId;
        }

        public List<ContListBean> getContList() {
            return contList;
        }

        public void setContList(List<ContListBean> contList) {
            this.contList = contList;
        }

    }

    public static class ContListBean {
        /**
         * contId : 1417576
         * name : 团伙扫码秒盗999元,竟偷到警察头上
         * pic : http://image2.pearvideo.com/cont/20180822/cont-1417576-11494817.jpg
         * nodeInfo : {"nodeId":"25","name":"一手Video","logoImg":"http://image.pearvideo.com/node/25-10027890-logo.jpg"}
         * link : http://
         * linkType : 0
         * cornerLabel : 3
         * cornerLabelDesc : 独播
         * forwordType : 1
         * videoType : 1
         * duration : 00'49"
         * liveStatus :
         * liveStartTime :
         * praiseTimes : 1295
         * adExpMonitorUrl :
         * coverVideo : http://video.pearvideo.com/head/20180822/cont-1417576-12719194.mp4
         */

        private String contId;
        private String name;
        private String pic;
        private NodeInfoBean nodeInfo;
        private String link;
        private String linkType;
        private String cornerLabel;
        private String cornerLabelDesc;
        private String forwordType;
        private String videoType;
        private String duration;
        private String liveStatus;
        private String liveStartTime;
        private String praiseTimes;
        private String adExpMonitorUrl;
        private String coverVideo;

        public String getContId() {
            return contId;
        }

        public void setContId(String contId) {
            this.contId = contId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public NodeInfoBean getNodeInfo() {
            return nodeInfo;
        }

        public void setNodeInfo(NodeInfoBean nodeInfo) {
            this.nodeInfo = nodeInfo;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getLinkType() {
            return linkType;
        }

        public void setLinkType(String linkType) {
            this.linkType = linkType;
        }

        public String getCornerLabel() {
            return cornerLabel;
        }

        public void setCornerLabel(String cornerLabel) {
            this.cornerLabel = cornerLabel;
        }

        public String getCornerLabelDesc() {
            return cornerLabelDesc;
        }

        public void setCornerLabelDesc(String cornerLabelDesc) {
            this.cornerLabelDesc = cornerLabelDesc;
        }

        public String getForwordType() {
            return forwordType;
        }

        public void setForwordType(String forwordType) {
            this.forwordType = forwordType;
        }

        public String getVideoType() {
            return videoType;
        }

        public void setVideoType(String videoType) {
            this.videoType = videoType;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getLiveStatus() {
            return liveStatus;
        }

        public void setLiveStatus(String liveStatus) {
            this.liveStatus = liveStatus;
        }

        public String getLiveStartTime() {
            return liveStartTime;
        }

        public void setLiveStartTime(String liveStartTime) {
            this.liveStartTime = liveStartTime;
        }

        public String getPraiseTimes() {
            return praiseTimes;
        }

        public void setPraiseTimes(String praiseTimes) {
            this.praiseTimes = praiseTimes;
        }

        public String getAdExpMonitorUrl() {
            return adExpMonitorUrl;
        }

        public void setAdExpMonitorUrl(String adExpMonitorUrl) {
            this.adExpMonitorUrl = adExpMonitorUrl;
        }

        public String getCoverVideo() {
            return coverVideo;
        }

        public void setCoverVideo(String coverVideo) {
            this.coverVideo = coverVideo;
        }

        public static class NodeInfoBean {
            /**
             * nodeId : 25
             * name : 一手Video
             * logoImg : http://image.pearvideo.com/node/25-10027890-logo.jpg
             */

            private String nodeId;
            private String name;
            private String logoImg;

            public String getNodeId() {
                return nodeId;
            }

            public void setNodeId(String nodeId) {
                this.nodeId = nodeId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLogoImg() {
                return logoImg;
            }

            public void setLogoImg(String logoImg) {
                this.logoImg = logoImg;
            }
        }
    }
}
