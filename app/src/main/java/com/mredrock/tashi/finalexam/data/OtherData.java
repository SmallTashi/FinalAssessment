package com.mredrock.tashi.finalexam.data;

import java.util.List;

public class OtherData {



    private String resultCode;
    private String resultMsg;
    private String reqId;
    private String systemTime;
    private CategoryInfoBean categoryInfo;
    private String nextUrl;
    private List<HotListBean> hotList;
    private List<ContListBean> contList;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public String getSystemTime() {
        return systemTime;
    }

    public void setSystemTime(String systemTime) {
        this.systemTime = systemTime;
    }

    public CategoryInfoBean getCategoryInfo() {
        return categoryInfo;
    }

    public void setCategoryInfo(CategoryInfoBean categoryInfo) {
        this.categoryInfo = categoryInfo;
    }

    public String getNextUrl() {
        return nextUrl;
    }

    public void setNextUrl(String nextUrl) {
        this.nextUrl = nextUrl;
    }

    public List<HotListBean> getHotList() {
        return hotList;
    }

    public void setHotList(List<HotListBean> hotList) {
        this.hotList = hotList;
    }

    public List<ContListBean> getContList() {
        return contList;
    }

    public void setContList(List<ContListBean> contList) {
        this.contList = contList;
    }

    public static class CategoryInfoBean {
        /**
         * categoryId : 10
         * name : 新知
         * color : #A2B0A0
         */

        private String categoryId;
        private String name;
        private String color;

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }

    public static class HotListBean {
        /**
         * contId : 1416662
         * name : 为什么清朝后宫中的秀女多丑女呢？
         * pic : http://image2.pearvideo.com/main/20180821/10000749-110419-0.png
         * nodeInfo : {"nodeId":"32","name":"左右视频","logoImg":"http://image.pearvideo.com/node/32-10915656-logo.png"}
         * link : http://
         * linkType : 0
         * cornerLabel :
         * cornerLabelDesc :
         * forwordType : 1
         * videoType : 1
         * duration : 04'17"
         * liveStatus :
         * liveStartTime :
         * praiseTimes : 772
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

        public static class NodeInfoBean {
            /**
             * nodeId : 32
             * name : 左右视频
             * logoImg : http://image.pearvideo.com/node/32-10915656-logo.png
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

    public static class ContListBean {
        /**
         * contId : 1417806
         * name : 白内障治疗难吗，手术后恢复慢吗？
         * pic : http://image.pearvideo.com/main/20180822/11131187-143608-0.png
         * nodeInfo : {"nodeId":"1727","name":"医视频","logoImg":"http://image2.pearvideo.com/node/1727-10571630-logo.png"}
         * link : http://
         * linkType : 0
         * cornerLabel :
         * cornerLabelDesc :
         * forwordType : 1
         * videoType : 1
         * duration : 05'16"
         * liveStatus :
         * liveStartTime :
         * praiseTimes : 0
         */

        private String contId;
        private String name;
        private String pic;
        private NodeInfoBeanX nodeInfo;
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

        public NodeInfoBeanX getNodeInfo() {
            return nodeInfo;
        }

        public void setNodeInfo(NodeInfoBeanX nodeInfo) {
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

        public static class NodeInfoBeanX {
            /**
             * nodeId : 1727
             * name : 医视频
             * logoImg : http://image2.pearvideo.com/node/1727-10571630-logo.png
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
