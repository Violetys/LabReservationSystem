maven项目，分为Android、web前端和java服务端，功能主要为用户查询并预约相应对象并控制相应对象的状态等等。对于预约操作使用synchronized进行同步。管理员可使用ajax异步提交人脸数据进行人脸识别登录或密码登录在web端查询实验室和设备的运行状态，登录后通过cookie和redis保存登录状态，使用token验证自动登录，采用分页展示大量数据。必要时通过socket向客户端发出警报，客户端与服务器通过json传送数据。
人脸识别使用到百度AI的sdk
