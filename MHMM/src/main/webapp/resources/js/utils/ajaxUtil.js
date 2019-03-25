const ajaxUtil = {
  /**
   * @param {String} url
   * @param {Function} predicate
   * @returns {String} responseText
   */
  sendGetAjax(url, predicate) {
    return new Promise((resolve, reject) => {
      const xhr = new XMLHttpRequest();
      xhr.onload = () => {
        if (predicate(xhr.status)) {
          resolve(xhr.responseText);
        } else {
          reject({ status: xhr.status, message: ajaxUtil.errorFormat(xhr.responseText) });
        }
      };
      xhr.open('GET', url, true);
      xhr.setRequestHeader('Content-type', 'application/json');
      xhr.send();
      xhr.onerror = () => reject(req.status);
    });
  },

  /**
   * @param {String} url
   * @param {Object} params
   * @param {Function} predicate
   * @returns {String} responseText
   */
  sendPostAjax(url, params, predicate) {
    return new Promise((resolve, reject) => {
      const xhr = new XMLHttpRequest();
      xhr.onload = function() {
        if (predicate(xhr.status)) {
          resolve(xhr.responseText);
        } else {
          reject({ status: xhr.status, message: ajaxUtil.errorFormat(xhr.responseText) });
        }
      };
      xhr.open('POST', url, true);
      xhr.setRequestHeader('Accept', 'application/json');
      xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
      xhr.send(JSON.stringify(params));
      xhr.onerror = () => reject(req.status);
    });
  },

  /**
   * @param {String} url
   * @param {Object} params
   * @param {Function} predicate
   * @returns {String} responseText
   */
  sendPutAjax(url, params) {
    return new Promise((resolve, reject) => {
      const xhr = new XMLHttpRequest();
      xhr.onload = function() {
        if (xhr.status < 300) {
          resolve(xhr.responseText);
        } else {
          reject({ status: xhr.status, message: ajaxUtil.errorFormat(xhr.responseText) });
        }
      };
      xhr.open('PUT', url, true);
      xhr.setRequestHeader('Content-type', 'application/json');
      xhr.send(JSON.stringify(params));
      xhr.onerror = () => reject(req.status);
    });
  },

  /**
   * @param {String} url
   * @param {Function} predicate
   * @returns {String} responseText
   */
  sendDeleteAjax(url, predicate) {
    return new Promise((resolve, reject) => {
      const xhr = new XMLHttpRequest();
      xhr.onload = () => {
        if (predicate(xhr.status)) {
          resolve(true);
        } else {
          reject({ status: xhr.status, message: ajaxUtil.errorFormat(xhr.responseText) });
        }
      };

      xhr.open('DELETE', url, true);
      xhr.setRequestHeader('Content-type', 'application/json');
      xhr.send();
      xhr.onerror = () => reject(req.status);
    });
  },

  /**
   * @param {String} url
   * @param {Object} params
   * @param {Function} predicate
   * @returns {String} responseText
   */
  sendPathAjax(url, params, predicate) {
    return new Promise((resolve, reject) => {
      const xhr = new XMLHttpRequest();
      xhr.onload = function() {
        if (predicate(xhr.status)) {
          resolve(xhr.responseText);
        } else {
          reject({ status: xhr.status, message: ajaxUtil.errorFormat(xhr.responseText) });
        }
      };
      xhr.open('PATCH', url, true);
      xhr.setRequestHeader('Content-type', 'application/json');
      xhr.send(JSON.stringify(params));
      xhr.onerror = () => reject(req.status);
    });
  },

  errorFormat(responseText) {
    let returnMessage = '';
    let parseText = JSON.parse(responseText);
    returnMessage += parseText.message;
    if (parseText.errors != null) {
      for (let error of parseText.errors) {
        returnMessage += `\n${error.reason}`;
      }
    }
    return returnMessage;
  }
};
