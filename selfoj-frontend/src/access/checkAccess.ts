import ACCESS_ENUM from "@/access/accessEnum";

/**
 * 检查权限
 * @param loginUser 当前登录用户
 * @param needAccess 需要有的权限
 * @return boolean 有无权限
 */
const checkAccess = (loginUser: any, needAccess = ACCESS_ENUM.NOT_LOGIN) => {
  //  获取当前登录用户 具有的权限
  const loginUserAccess = loginUser?.userRole ?? ACCESS_ENUM.NOT_LOGIN;
  //  如果没有loginUser 即为未登录
  if (needAccess === ACCESS_ENUM.NOT_LOGIN) {
    return true;
  }
  //  如果登录用户才能访问
  if (needAccess === ACCESS_ENUM.USER) {
    //  如果登录用户没登录 表示无权限
    if (loginUserAccess === ACCESS_ENUM.NOT_LOGIN) {
      return false;
    }
  }

  //  如果需要管理员权限
  if (needAccess === ACCESS_ENUM.ADMIN) {
    //  如果登录用户不是管理员 表示无权限
    if (loginUserAccess !== ACCESS_ENUM.ADMIN) {
      return false;
    }
  }
  return true;
};

export default checkAccess;
