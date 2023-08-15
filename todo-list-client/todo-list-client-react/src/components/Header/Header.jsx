import React, { Component } from 'react'
import './Header.css'

export default class Header extends Component {
  state = {
    inputRef: React.createRef(),
  }

  handleKeyUp = (e) => {
    const { inputRef } = this.state
    const { onAddItem } = this.props

    if (e.key == 'Enter') {
      // 获取输入框的值
      const inputValue = e.target.value
      onAddItem(inputValue)
      inputRef.current.value = ''
    }
  }

  render() {
    const { inputRef } = this.state

    return (
      <div className="header">
        <input
          type="text"
          ref={inputRef}
          className="mo-input header__input"
          placeholder="输入内容"
          onKeyUp={this.handleKeyUp}
        />
      </div>
    )
  }
}
