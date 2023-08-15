import React, { Component } from 'react'
import PropTypes from 'prop-types'

import './Item.css'

export default class Item extends Component {
  static propTypes = {
    id: PropTypes.string.isRequired,
    title: PropTypes.string.isRequired,
    selected: PropTypes.bool.isRequired,
    onRemoveItem: PropTypes.func.isRequired,
    onItemSelectedChange: PropTypes.func.isRequired,
  }

  handleSelectedChange = (e) => {
    const { id, onItemSelectedChange } = this.props
    console.log(e.target.checked)
    onItemSelectedChange({ id, selected: e.target.checked })
  }

  handleItemRemove = (e) => {
    e.preventDefault()

    const { id, onRemoveItem } = this.props
    if (window.confirm('确定移除？')) {
      onRemoveItem(id)
    }
  }

  render() {
    const { id, title, selected, onRemoveItem } = this.props

    return (
      <label className="item">
        <input
          type="checkbox"
          checked={selected}
          onChange={this.handleSelectedChange}
        />
        <div className="item__title">{title}</div>
        <div
          className="item__delete"
          onClick={this.handleItemRemove}
        >
          移除
        </div>
      </label>
    )
  }
}
