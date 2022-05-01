

const validityMessage = {
      badInput: '[input] 잘못된 입력입니다.',
      patternMismatch: '[input] 패턴에 맞게 입력하세요',
      rangeOverflow: '[input] 범위를 초과하였습니다',
      rangeUnderflow: '[input] 범위에 미달하였습니다',
      stepMismatch: '[input] 간격에 맞게 입력하세요',
      tooLong: '[input] 최대 글자 미만으로 입력하세요',
      tooShort: '[input] 최소 글자 이상으로 입력하세요',
      typeMismatch: '[input] 형식에 맞게 입력하세요',
      valueMissing: '[input] 이 필드를 반드시 입력하세요',
    }


function getMessage(validity) {
      for (const key in validityMessage) {
        if (validity[key]) {
          return validityMessage[key]
        }
      }
    }


function showError(input) {
      // 오류 메시지 스타일 
      document.querySelector('#showError').textContent = getMessage(input.validity)
    }
